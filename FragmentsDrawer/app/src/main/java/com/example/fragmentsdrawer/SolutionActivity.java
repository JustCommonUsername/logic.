package com.example.fragmentsdrawer;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.adapters.SolutionPartsAdapter;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.models.SolutionsViewModel;
import com.example.fragmentsdrawer.ui.home.solutions.SolutionFragment;

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
