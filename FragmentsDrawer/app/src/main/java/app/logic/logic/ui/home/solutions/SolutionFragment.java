package app.logic.logic.ui.home.solutions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import app.logic.logic.R;
import app.logic.logic.SolutionActivity;
import app.logic.logic.adapters.SolutionCardsAdapter;
import app.logic.logic.models.CalculatorViewModel;
import app.logic.logic.util.SolutionInfo;

import java.util.ArrayList;

public class SolutionFragment extends Fragment {

    private CalculatorViewModel viewModel;
    private SolutionInfo[] solutions;
    private ListView container;

    public static final String VIEW_SHARED_NAME = "sharedName";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle onSavedInstanceState) {
        return inflater.inflate(R.layout.solutions_layout_part, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        container = view.findViewById(R.id.solution_items_list_container);

        try {
            viewModel = ViewModelProviders.of(getActivity()).get(CalculatorViewModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        solutions = new SolutionInfo[] {
                new SolutionInfo(getResources().getString(R.string.solution_item_name_cdnf_card), viewModel.getSolvedEquation().getDNF()),
                new SolutionInfo(getResources().getString(R.string.solution_item_name_ccnf_card), viewModel.getSolvedEquation().getCNF())
        };

        SolutionCardsAdapter adapter = new SolutionCardsAdapter(getContext(), new ArrayList<SolutionInfo>());

        adapter.addAll(solutions);

        CardView header = (CardView) getLayoutInflater().inflate(R.layout.solution_item_card_extended, container, false);

        container.setAdapter(adapter);
        container.addHeaderView(header);

        final TextView name = header.findViewById(R.id.solution_item_name);
        final TextView manual = header.findViewById(R.id.solution_manual_string);
        final TextView function = header.findViewById(R.id.solution_item_verbose);
        final Button action = header.findViewById(R.id.solution_action_next);

        name.setText(getResources().getString(R.string.solution_item_name_function_card));
        manual.setText(viewModel.getSolvedEquation().getFunction());
        function.setText(viewModel.getSolvedEquation().getReducedFunction());

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SolutionActivity.class);
                i.putExtra(VIEW_SHARED_NAME, "solution_manual_shared");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), manual, "solution_manual_shared");

                startActivity(i, options.toBundle());
            }
        });
    }

}
