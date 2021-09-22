package app.logic.logic;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import app.logic.logic.R;

import app.logic.logic.adapters.SolutionPartsAdapter;
import app.logic.logic.models.SolutionsViewModel;
import app.logic.logic.ui.home.solutions.SolutionFragment;

public class SolutionActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private RecyclerView scrollSolutions;
    private SolutionPartsAdapter adapter;
    private View tintView;

    private SolutionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.solutions_main);

        toolbar = findViewById(R.id.toolbar_consistent_solution);
        scrollSolutions = findViewById(R.id.scroll_solutions);
        tintView = findViewById(R.id.solution_main_tint);

        Bundle bundle = getIntent().getExtras();
        String sharedName = bundle.getString(SolutionFragment.VIEW_SHARED_NAME);

        toolbar.setTitle(R.string.solution_steps);

        viewModel = ViewModelProviders.of(this).get(SolutionsViewModel.class);

        adapter = new SolutionPartsAdapter(this, viewModel.getCurrentSolvedEquation(), tintView, sharedName);

        scrollSolutions.setAdapter(adapter);

        toolbar.setNavigationIcon(R.drawable.ic_solution_part_close_detailed_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        tintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getVisibility() == View.VISIBLE) {
                    int focused = adapter.getFocusedPosition();

                    adapter.setFocusedPosition(-1);
                    adapter.notifyItemChanged(focused);

                    v.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
