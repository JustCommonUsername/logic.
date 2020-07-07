package com.example.fragmentsdrawer;

import android.os.Bundle;
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

public class SolutionActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private RecyclerView scrollSolutions;
    private SolutionPartsAdapter adapter;

    private SolutionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solutions_main);

        toolbar = findViewById(R.id.toolbar_consistent_solution);
        scrollSolutions = findViewById(R.id.scroll_solutions);

        setSupportActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(SolutionsViewModel.class);

        adapter = new SolutionPartsAdapter(this, viewModel.getCurrentSolvedEquation().getSteps());

        scrollSolutions.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

}
