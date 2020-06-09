package com.example.fragmentsdrawer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragmentsdrawer.ui.home.calculator.CalculatorFragment;
import com.example.fragmentsdrawer.ui.home.equations.EquationsFragment;
import com.example.fragmentsdrawer.ui.home.history.HistoryFragment;

public class CollectionPagerAdapter extends FragmentStateAdapter {

    public CollectionPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new HistoryFragment();
            case 0:
            default:
                return new CalculatorFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
