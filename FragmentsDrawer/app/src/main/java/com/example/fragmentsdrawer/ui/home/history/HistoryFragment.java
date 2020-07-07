package com.example.fragmentsdrawer.ui.home.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.adapters.HistoryItemSwipeHelper;
import com.example.fragmentsdrawer.adapters.HistoryViewAdapter;
import com.example.fragmentsdrawer.databinding.HomeHistoryBinding;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;

import java.util.Collections;
import java.util.List;

public class HistoryFragment extends Fragment {

    private List<Equation> mWorldList;
    private HistoryViewAdapter recyclerAdapter;
    private CalculatorViewModel calculatorViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeHistoryBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_history,
                container,
                false);

        calculatorViewModel = ViewModelProviders.of(getActivity()).get(CalculatorViewModel.class);
        binding.setViewmodel(calculatorViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Getting a reference to the recycler view in history fragment layout
        RecyclerView mRecyclerView = view.findViewById(R.id.elements_recycler);
        // Creating an adapter and passing data to it
        recyclerAdapter = new HistoryViewAdapter(getActivity().getApplicationContext(), mWorldList, Navigation.findNavController(getActivity(), R.id.nav_host_fragment),
                calculatorViewModel, (RelativeLayout) view.findViewById(R.id.history_view_globe));
        // Supplying recycler view with adapter
        mRecyclerView.setAdapter(recyclerAdapter);
        // Giving recycler view vertical type of orientation
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Creating an instance of touch helper, which is responsible for handling swipes
        ItemTouchHelper touchHelper = new ItemTouchHelper(new HistoryItemSwipeHelper(recyclerAdapter));
        // Attaching ItemTouchHelper instance to RecyclerView, created in method
        touchHelper.attachToRecyclerView(mRecyclerView);

        calculatorViewModel.getAllEquations().observe(this, new Observer<List<Equation>>() {
            @Override
            public void onChanged(List<Equation> equations) {
                // Setting data to the adapter
                recyclerAdapter.setEquations(equations);
            }
        });
    }

}
