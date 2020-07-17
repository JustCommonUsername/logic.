package com.example.fragmentsdrawer.ui.home.calculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.inputmethodservice.Keyboard;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.MainActivity;
import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.adapters.CalculatorEditorViewAdapter;
import com.example.fragmentsdrawer.core.EquationFactory;
import com.example.fragmentsdrawer.core.IllegalLogicEquationException;
import com.example.fragmentsdrawer.databinding.HomeCalculatorBinding;
import com.example.fragmentsdrawer.databinding.HomeCalculatorBindingImpl;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;
import com.example.fragmentsdrawer.services.KeyboardInputService;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import kotlin.TypeCastException;

public class CalculatorFragment extends Fragment {

    private RecyclerView container;
    private CalculatorViewModel viewModel;
    private TextView answerPreview;
    private FragmentActivity activity;
    private Button action;
    private NavController controller;
    private CalculatorEditorViewAdapter adapter;
    private HomeCalculatorBinding binding;

    public static final int KEYCODE_DELETE = 0;
    public static final int EXTRA_INFO = -20;
    public static final int EXTRA_LETTERS = -30;
    public static final int LEFT_BRACKET = -100;
    public static final int RIGHT_BRACKET = -200;
    public static final int HIDE_KEYBOARD = -1000;

    private CalculatorBroadcastReceiver receiver;

    @Override
    public View onCreateView(LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_calculator,
                container,
                false);

        viewModel = ViewModelProviders.of(activity).get(CalculatorViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        final View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(final @NonNull View view, Bundle savedInstanceState) {
        // Getting reference to calculator fields recycler
        container = view.findViewById(R.id.calculator_fields_scroller);

        answerPreview = view.findViewById(R.id.calculator_preview_answer);
        action = view.findViewById(R.id.to_solutions);
        controller = NavHostFragment.findNavController(this);

        adapter = new CalculatorEditorViewAdapter(getContext(), new ArrayList<Character>(), viewModel, getActivity());

        container.setAdapter(adapter);

        receiver = new CalculatorBroadcastReceiver();

        container.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    MainActivity.showKeyboardFrom(activity.getApplicationContext(), container);
                else
                    MainActivity.hideKeyboardFrom(activity.getApplicationContext(), CalculatorFragment.this.getView().getRootView());
            }
        });

        // Handling change to the current value of equation in View Model
        viewModel.getCurrentEquation().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                SolvingTask solving = new SolvingTask();
                // Executing solving algorithm in background thread
                solving.execute(s);
            }
        });

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    controller.navigate(R.id.action_nav_home_to_nav_home_solution);
                    if (viewModel.getSolvedEquation() != null)
                        viewModel.insert(viewModel.getSolvedEquation());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        // Registering receiver with given IntentFilter
        try {
            getActivity().registerReceiver(receiver, new IntentFilter(KeyboardInputService.CHANNEL));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (viewModel.getCurrentEquation().getValue() != null) {
            // Returning the once given input to the editor
            ArrayList<Character> list = new ArrayList<>(viewModel.getCurrentEquation().getValue().length());

            // Adding zero character
            list.add('\0');

            for (char character : viewModel.getCurrentEquation().getValue().toCharArray())
                list.add(character);

            adapter.onResume(list);
        } else
            viewModel.getIsEquationEmpty().setValue(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        // Handling the keyboard close when the fragment is not seen
        MainActivity.hideKeyboardFrom(activity.getApplicationContext(), this.getView().getRootView());

        // Saving value from the adapter
        viewModel.getCurrentEquation().postValue(adapter.fields());

        if (TextUtils.isEmpty(viewModel.getCurrentEquation().getValue()))
            viewModel.getIsEquationEmpty().postValue(true);

        // Unregistering receiver from a fragment to avoid memory leaks
        try {
            getActivity().unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**<summary>
     * Class below is responsible for receiving Broadcast calls.
     * Broadcast channel is connected to KeyboardInputService, which
     * incapsulates layout of the custom keyboard and its logic.
     * Choosing between broadcasts is made by switch structure.
     *
     * The <b>default</b> part of switch is needed to handle events,
     * when a character is via keyboard. It inflates new EditText and
     * gives it its value. Additionally, when receiving Intent with the character
     * '(', algorithm in <b>default</b> adds a new EditText with the value ')' in it,
     * making complete parenthesis structure, and adds new EditTexts to the special list.
     *
     * MutableLiveData <b>"bracketData"</b> is an observable object, which handles changing
     * of its value (here, it is List<Pair<EditText, EditText>>). Usage of EditTexts values
     * in the list is questionable, as Context of given Fragment can be changed at any moment,
     * therefore such pattern will be changed in later commits.
     * </summary>**/
    private class CalculatorBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (container != null) {

                final int value = intent.getIntExtra(KeyboardInputService.INFO, 0);

                final EditText focusedChild = (EditText)container.getFocusedChild();
                int index;

                try {
                    index = container.getChildAdapterPosition(focusedChild);
                } catch (NullPointerException e) {
                    return;
                }

                // Changing mode of a observable data so, that there is no pending problem
                viewModel.getIsExceptionOccured().setValue(false);

                viewModel.getIsEquationEmpty().setValue(false);

                switch (value) {
                    case EXTRA_INFO:
                    case EXTRA_LETTERS:
                        // TODO: Implement keyboard key response
                        break;
                    case KEYCODE_DELETE:
                        // TODO: Check situation work below
                        String text = intent.getStringExtra(KeyboardInputService.TEXT);
                        if (text != null)
                            adapter.delete(text.toCharArray()[0], index);
                        break;
                    case Keyboard.EDGE_LEFT:
                        adapter.changePosition(index - 1);
                        container.smoothScrollToPosition(index > 0 ? index - 1 : 0);
                        break;
                    case Keyboard.EDGE_RIGHT:
                        adapter.changePosition(index + 1);
                        container.smoothScrollToPosition(index < adapter.getItemCount() ? index + 1 : adapter.getItemCount());
                        break;
                    case Keyboard.KEYCODE_DONE:
                        String equation = adapter.fields();

                        if (TextUtils.isEmpty(equation))
                            viewModel.getIsEquationEmpty().setValue(true);
                        else
                            viewModel.getCurrentEquation().setValue(equation);

                        break;
                    case HIDE_KEYBOARD:
                    case Keyboard.KEYCODE_CANCEL:
                        break;
                    default:
                        adapter.add((char)value, index);
                        container.smoothScrollToPosition(adapter.getItemCount());
                }

            }
        }

    }

    private class SolvingTask extends AsyncTask<String, Void, Equation> {

        @Override
        public Equation doInBackground(String... params) {
            Equation equation;

            try {
                equation = EquationFactory.construct(params[0]);
            } catch (IllegalLogicEquationException e) {
                return null;
            }

            return equation;
        }

        @Override
        public void onPostExecute(Equation result) {
            if (result == null && viewModel.getCurrentEquation().getValue() != null) {
                viewModel.setSolvedEquation(null);
                viewModel.getIsEquationEmpty().postValue(true);
                viewModel.getIsExceptionOccured().postValue(true);
            } else if (result != null) {
                viewModel.getIsEquationEmpty().postValue(false);
                answerPreview.setText("= " + result.getReducedFunction());
                viewModel.setSolvedEquation(result);
            } else
                viewModel.getIsEquationEmpty().postValue(true);
        }

    }

}
