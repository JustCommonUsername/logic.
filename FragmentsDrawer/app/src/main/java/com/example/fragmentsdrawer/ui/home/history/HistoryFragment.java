package com.example.fragmentsdrawer.ui.home.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.adapters.HistoryViewAdapter;
import com.example.fragmentsdrawer.databinding.HomeHistoryBinding;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;

import java.util.List;

public class HistoryFragment extends Fragment {

    private List<Equation> mWorldList;
    private RecyclerView mRecyclerView;
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
        mRecyclerView = view.findViewById(R.id.elements_recycler);
        // Creating an adapter and passing data to it
        recyclerAdapter = new HistoryViewAdapter(getContext(), mWorldList, Navigation.findNavController(getActivity(), R.id.nav_host_fragment), calculatorViewModel);
        // Supplying recycler view with adapter
        mRecyclerView.setAdapter(recyclerAdapter);
        // Giving recycler view vertical type of orientation
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        calculatorViewModel.getAllEquations().observe(this, new Observer<List<Equation>>() {
            @Override
            public void onChanged(List<Equation> equations) {
                recyclerAdapter.setEquations(equations);
            }
        });
    }

}
