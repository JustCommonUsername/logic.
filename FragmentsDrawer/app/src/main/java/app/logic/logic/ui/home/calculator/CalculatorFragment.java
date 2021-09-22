package app.logic.logic.ui.home.calculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.inputmethodservice.Keyboard;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import app.logic.logic.MainActivity;
import app.logic.logic.R;
import app.logic.logic.adapters.CalculatorEditorViewAdapter;
import app.logic.logic.core.EquationFactory;
import app.logic.logic.core.IllegalLogicEquationException;
import app.logic.logic.databinding.HomeCalculatorBinding;
import app.logic.logic.models.CalculatorViewModel;
import app.logic.logic.rooms.Equation;
import app.logic.logic.services.KeyboardInputService;
import com.google.android.material.snackbar.Snackbar;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment {

    private RecyclerView container;
    private CalculatorViewModel viewModel;
    private TextView answerPreview;
    private FragmentActivity activity;
    private Button action;
    private NavController controller;
    private CalculatorEditorViewAdapter adapter;
    private HomeCalculatorBinding binding;
    private String previousEquation = "";

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

        final String currentEquation;
        final ArrayList<String> list = new ArrayList<>();

        if ((currentEquation = viewModel.getCurrentEquation()) != null) {
            for (char character: currentEquation.toCharArray())
                list.add(Character.toString(character));
        }

        adapter = new CalculatorEditorViewAdapter(getContext(), list, viewModel, getActivity());

        container.setAdapter(adapter);

        receiver = new CalculatorBroadcastReceiver();

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showKeyboardFrom(activity.getApplicationContext(), container);
            }
        });

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (viewModel.getSolvedEquation() != null) {
                        controller.navigate(R.id.action_nav_home_to_nav_home_solution);
                        viewModel.insert(viewModel.getSolvedEquation());
                    } else
                        Snackbar.make(binding.getRoot(),
                                getResources().getString(R.string.home_calculator_snackbar_warning),
                                Snackbar.LENGTH_SHORT)
                                .setTextColor(getResources().getColor(R.color.primaryColor))
                                .show();
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

        if (!TextUtils.isEmpty(viewModel.getCurrentEquation())) {
            final Equation equation = viewModel.getSolvedEquation();

            if (equation != null)
                answerPreview.setText("= " + equation.getReducedFunction());
        }
        else
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

        final String value;

        if ((value = adapter.fields()).equals("\0") || TextUtils.isEmpty(value)) {
            viewModel.setCurrentEquation(null);
            viewModel.setSolvedEquation(null);
        }
        else
            // Saving value from the adapter
            viewModel.setCurrentEquation(adapter.fields());

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
     * </summary>**/
    private class CalculatorBroadcastReceiver extends BroadcastReceiver implements
            CalculatorEditorViewAdapter.OnFieldChangedCallback {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (container != null) {

                final int value = intent.getIntExtra(KeyboardInputService.INFO, 0);

                final EditText focusedChild = (EditText)container.getFocusedChild();

                if (focusedChild == null)
                    return;

                final int index = container.getChildAdapterPosition(focusedChild);

                boolean isErrorOccured = false;

                switch (value) {
                    case EXTRA_INFO:
                    case EXTRA_LETTERS:
                        // TODO: Implement keyboard key response
                        break;
                    case KEYCODE_DELETE:
                        String text = focusedChild.getText().toString();

                        if (!TextUtils.isEmpty(text)) {
                            if ((text.equals("(") || text.equals(")")) && adapter.getItemCount() > 1)
                                adapter.delete(text, index);
                            else
                                adapter.set(null, index);
                        }
                        else if (adapter.getItemCount() > 1)
                            adapter.delete(index);
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
                        final String s = adapter.fields();

                        isErrorOccured = viewModel.getIsExceptionOccured().getValue();

                        if (TextUtils.isEmpty(s))
                            viewModel.getIsEquationEmpty().setValue(true);
                        else {
                            if (!TextUtils.isEmpty(s) && !s.equals(previousEquation)) {
                                viewModel.setCurrentEquation(s);
                                SolvingTask solving = new SolvingTask();
                                // Executing solving algorithm in background thread
                                solving.execute(s);
                                previousEquation = s;
                            }
                        }

                        break;
                    case HIDE_KEYBOARD:
                    case Keyboard.KEYCODE_CANCEL:
                        break;
                    default:
                        if (adapter.get(index) != null) {
                            adapter.add(Character.toString((char) value), index);
                            container.smoothScrollToPosition(adapter.getItemCount());
                        }
                        else if (value >= 0)
                            adapter.set(Character.toString((char) value), index);
                }

                // Setting exception situation label
                viewModel.getIsExceptionOccured().setValue(isErrorOccured);

                /**
                 * @see {@link CalculatorEditorViewAdapter.OnFieldChangedCallback#onFieldChange(int, int)}
                 */
                onFieldChange(value, index);
            }
        }

        @Override
        public void onFieldChange(int primaryCode, int index) {
            if (index >= 0 && adapter.getNullIndex(index) != -1)
                adapter.delete(adapter.getNullIndex(index));
        }

    }

    private class SolvingTask extends AsyncTask<String, Void, Equation> {

        @Override
        public Equation doInBackground(String... params) {
            Equation equation;

            try {
                equation = new EquationFactory().construct(params[0]);
            } catch (IllegalLogicEquationException | ParseCancellationException e) {
                return null;
            }

            return equation;
        }

        @Override
        public void onPostExecute(Equation result) {
            if (result == null && isEquationValid(viewModel.getCurrentEquation())) {
                viewModel.setSolvedEquation(null);
                viewModel.getIsEquationEmpty().setValue(true);
                viewModel.getIsExceptionOccured().setValue(true);
            } else if (result != null) {
                viewModel.getIsEquationEmpty().setValue(false);
                answerPreview.setText("= " + result.getReducedFunction());
                viewModel.setSolvedEquation(result);
            } else
                viewModel.getIsEquationEmpty().setValue(true);
        }

        private boolean isEquationValid(String equation) {
            return equation != null && !equation.equals("\0") && equation.length() > 0;
        }

    }

}
