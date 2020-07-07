package com.example.fragmentsdrawer.ui.home.solutions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.models.CalculatorViewModel;

public class SolutionFragment extends Fragment {

    private CardView function, CDNF, CCNF;
    private CalculatorViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle onSavedInstanceState) {
        return inflater.inflate(R.layout.solutions_layout_part, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        function = view.findViewById(R.id.function);
        CCNF = view.findViewById(R.id.ccnf);
        CDNF = view.findViewById(R.id.cdnf);

        function.findViewById(R.id.solution_manual_scroll).setVisibility(View.VISIBLE);
        function.findViewById(R.id.solution_wide_description).setVisibility(View.VISIBLE);
        function.findViewById(R.id.solution_arrow_image).setVisibility(View.VISIBLE);

        ((TextView)function.findViewById(R.id.solution_item_name)).setText("Function");
        ((TextView)CCNF.findViewById(R.id.solution_item_name)).setText("CCNF");
        ((TextView)CDNF.findViewById(R.id.solution_item_name)).setText("CDNF");

        try {
            viewModel = ViewModelProviders.of(getActivity()).get(CalculatorViewModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView answer = function.findViewById(R.id.solution_item_verbose);
        TextView ccnf = CCNF.findViewById(R.id.solution_item_verbose);
        TextView cdnf = CDNF.findViewById(R.id.solution_item_verbose);
        TextView manual = function.findViewById(R.id.solution_manual_string);

        try {
            answer.setText(viewModel.getSolvedEquation().getReducedFunction());
            ccnf.setText(viewModel.getSolvedEquation().getCNF());
            cdnf.setText(viewModel.getSolvedEquation().getDNF());
            manual.setText(viewModel.getSolvedEquation().getFunction());
        } catch (NullPointerException e) {
            answer.setText("Re-enter input");
            ccnf.setText("Re-enter input");
            cdnf.setText("Re-enter input");
        }
    }

}
