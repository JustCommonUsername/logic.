package com.example.fragmentsdrawer.ui.home.calculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
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
import androidx.navigation.fragment.NavHostFragment;

import com.example.fragmentsdrawer.MainActivity;
import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.core.EquationFactory;
import com.example.fragmentsdrawer.core.IllegalLogicEquationException;
import com.example.fragmentsdrawer.databinding.HomeCalculatorBinding;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;
import com.example.fragmentsdrawer.services.KeyboardInputService;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import kotlin.TypeCastException;

public class CalculatorFragment extends Fragment implements View.OnFocusChangeListener {

    private LinearLayout container;
    private CalculatorViewModel viewModel;
    private TextView answerPreview;
    private FragmentActivity activity;
    private Button action;
    private NavController controller;
    private NestedScrollView overallScroll;

    private Drawable editTextMainBackground;

    private MutableLiveData<LinkedList<Pair<EditText, EditText>>> bracketsData = new MutableLiveData<>();
    private LinkedList<Pair<EditText, EditText>> brackets = new LinkedList<>();

    private float DENSITY;
    public static final int ADD_FORM = 0;
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
        HomeCalculatorBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_calculator,
                container,
                false);

        viewModel = ViewModelProviders.of(activity).get(CalculatorViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(final @NonNull View view, Bundle savedInstanceState) {
        container = view.findViewById(R.id.calculator_container);
        answerPreview = view.findViewById(R.id.calculator_preview_answer);
        action = view.findViewById(R.id.to_solutions);
        controller = NavHostFragment.findNavController(this);
        overallScroll = view.findViewById(R.id.calculator_overall_scroll);

        viewModel.getIsExceptionOccured().setValue(false);

        DENSITY = getContext().getResources().getDisplayMetrics().scaledDensity;

        editTextMainBackground = getLayoutInflater()
                .inflate(R.layout.calculator_edit_text, null, false)
                .getBackground();

        receiver = new CalculatorBroadcastReceiver(view);
        bracketsData.setValue(brackets);

        container.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    MainActivity.showKeyboardFrom(activity.getApplicationContext(), container);
                else
                    MainActivity.hideKeyboardFrom(activity.getApplicationContext(), CalculatorFragment.this);
            }
        });

        // Handling change to the current value of equation in View Model
        viewModel.getCurrentEquation().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Equation equation;

                try {
                    equation = new EquationFactory().construct(s);
                } catch (IllegalLogicEquationException e) {
                    viewModel.getIsExceptionOccured().postValue(true);
                    return;
                }

                if (s == null)
                    viewModel.getCurrentEquation().setValue(null);
                else
                    answerPreview.setText("= " + s);

                if (!TextUtils.isEmpty(s) && equation != null)
                    viewModel.insert(equation);
            }
        });

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    controller.navigate(R.id.nav_home_solution);
                } catch (NullPointerException e) {
                    Toast.makeText(getContext(), "No NavController found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bracketsData.observe(this, new Observer<LinkedList<Pair<EditText, EditText>>>() {
            @Override
            public void onChanged(LinkedList<Pair<EditText, EditText>> pairs) {
                // TODO: Exclude debugging after check
                for (int i = pairs.size() - 1; i >= 0; i--) {
                    try {
                        EditText first = pairs.get(i).first, second = pairs.get(i).second;

                        first.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23 + i);
                        second.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23 + i);
                    } catch (ClassCastException e) {
                        Toast.makeText(getContext(), "Found View by ID is not EditText", Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException e) {
                        Toast.makeText(getContext(), "No View by given ID found", Toast.LENGTH_SHORT).show();
                    }
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

        // Forcing LiveData to observe its value
        viewModel.getCurrentEquation().postValue(viewModel.getCurrentEquation().getValue());

        // Adding EditTexts, that were deleted
        if (viewModel.getCurrentEquation().getValue() != null && container.getChildCount() == 2
                && ((EditText)container.getChildAt(1)).getText().toString().length() == 0) {
            for (char ch: viewModel.getCurrentEquation().getValue().toCharArray()) {
                container.addView(getLayoutInflater().inflate(R.layout.calculator_edit_text, container, false));
                ((EditText)container.getChildAt(container.getChildCount() - 1)).setText(Character.toString(ch));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Handling the keyboard close when the fragment is not seen
        MainActivity.hideKeyboardFrom(activity.getApplicationContext(), this);

        // Saving current data
        String toSafe = "";
        for (int i = 1; i < container.getChildCount(); i++) {
            toSafe += ((TextView)container.getChildAt(i)).getText().toString();
        }
        viewModel.getCurrentEquation().setValue(toSafe);

        // Unregistering receiver from a fragment to avoid memory leaks
        try {
            getActivity().unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Clearing data about brackets in order to prevent from memory leak
        bracketsData.getValue().clear();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            v.setBackground(editTextMainBackground);
        else
            v.setBackgroundResource(android.R.color.transparent);
    }

    private <T extends EditText> void addValueToList(T a, T b) {
        brackets.push(new Pair<EditText, EditText>(a, b));
        bracketsData.postValue(brackets);
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

        private View parentFragment;

        CalculatorBroadcastReceiver(View view) {
            this.parentFragment = view;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (container != null) {
                final EditText focusedChild;
                final String focusedText;

                try {
                    focusedChild = (EditText)container.getFocusedChild();
                    focusedText = focusedChild.getText().toString();
                } catch (NullPointerException e) {
                    return;
                }

                final int focusedChildIndex = container.indexOfChild(focusedChild);
                final int value = intent.getIntExtra(KeyboardInputService.INFO, 0);

                // Changing mode of a observable data so, that there is no pending problem
                viewModel.getIsExceptionOccured().setValue(false);

                switch (value) {
                    case EXTRA_INFO:
                    case EXTRA_LETTERS:
                        // TODO: Implement keyboard key response
                        break;
                    case Keyboard.KEYCODE_DELETE:
                        // TODO: Check situation work below
                        viewModel.getCurrentEquation().setValue(null);

                        boolean isBracket = focusedText.equals("(") || focusedText.equals(")");

                        if (isBracket) {
                            for (int i = 0; i < bracketsData.getValue().size(); i++) {
                                EditText first = bracketsData.getValue().get(i).first;
                                EditText second = bracketsData.getValue().get(i).second;

                                if (first == focusedChild || second == focusedChild) {
                                    container.removeView(first);
                                    container.removeView(second);

                                    brackets.remove(i);
                                    bracketsData.postValue(brackets);

                                    if (container.getChildAt(focusedChildIndex - 1) == null)
                                        container.getChildAt(container.getChildCount() - 1).requestFocus();
                                    else
                                        container.getChildAt(focusedChildIndex - 1).requestFocus();

                                    break;
                                }
                            }
                        } else
                            container.removeView(focusedChild);

                        if (container.getChildCount() == 1) {
                            container.addView(getLayoutInflater().inflate(R.layout.calculator_edit_text, container, false));
                            container.getChildAt(1).requestFocus();
                        }
                        else
                            container.getChildAt(focusedText.equals(")") ? focusedChildIndex - 2 : focusedChildIndex - 1).requestFocus();

                        break;
                    case Keyboard.KEYCODE_CANCEL:
                        try {
                            MainActivity.hideKeyboardFrom(getContext(), CalculatorFragment.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case Keyboard.EDGE_LEFT:
                        try {
                            if (focusedChildIndex > 1) {
                                if (TextUtils.isEmpty(focusedText))
                                    container.removeView(focusedChild);
                                container.getChildAt(focusedChildIndex - 1).requestFocus();
                            }
                        } catch (NullPointerException e) {
                            refreshLayout();
                        }
                        break;
                    case Keyboard.EDGE_RIGHT:
                        try {
                            if (focusedChildIndex + 1 < container.getChildCount()) {
                                if (TextUtils.isEmpty(focusedText))
                                    container.removeView(focusedChild);
                                container.getChildAt(focusedChildIndex + 1).requestFocus();
                            }
                        } catch (NullPointerException e) {
                            refreshLayout();
                        }
                        break;
                    case Keyboard.KEYCODE_DONE:
                        StringBuffer equation = new StringBuffer();

                        try {
                            // Building the current equation, which was added by user
                            for (int i = 1; i < container.getChildCount(); i++) {
                                TextView current = (TextView)container.getChildAt(i);
                                equation.append(current.getText().toString());
                            }
                        } catch (ClassCastException e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        } finally {
                            viewModel.getCurrentEquation().setValue(equation.toString());
                        }

                        break;
                    case HIDE_KEYBOARD:
                        if (getContext() != null)
                            MainActivity.hideKeyboardFrom(getContext(), CalculatorFragment.this);
                        break;
                    default:
                        viewModel.getCurrentEquation().setValue(null);

                        container.addView(activity.getLayoutInflater().inflate(
                                R.layout.calculator_edit_text, container, false),
                                focusedChildIndex + 1
                        );

                        final EditText form = (EditText)container.getChildAt(focusedChildIndex + 1);
                        form.setText(Character.toString((char)value));
                        form.setOnFocusChangeListener(CalculatorFragment.this);
                        form.requestFocus();

                        if (Character.toString((char)value).equals("(")) {
                            container.addView(activity.getLayoutInflater().inflate(
                                    R.layout.calculator_edit_text, container, false),
                                    focusedChildIndex + 2);
                            EditText secondBracket = (EditText)container.getChildAt(focusedChildIndex + 2);
                            secondBracket.setOnFocusChangeListener(CalculatorFragment.this);
                            secondBracket.setText(")");

                            addValueToList(form, secondBracket);
                        }
                }
            }
        }

        private void refreshLayout() {
            int steps = container.getChildCount();
            for (int i = steps; i > 2; i--) {
                container.removeView(container.getChildAt(i - 1));
            }
            Snackbar.make(overallScroll, R.string.home_snackbar_alert, Snackbar.LENGTH_SHORT)
                    .setTextColor(getResources().getColor(R.color.primaryColor))
                    .show();
        }

    }

}
