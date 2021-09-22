package app.logic.logic.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.logic.logic.ui.home.calculator.CalculatorFragment;
import app.logic.logic.ui.home.history.HistoryFragment;

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
