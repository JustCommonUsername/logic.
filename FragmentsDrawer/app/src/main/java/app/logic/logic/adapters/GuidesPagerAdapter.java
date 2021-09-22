package app.logic.logic.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.logic.logic.ui.guide.GuideFragment;

public class GuidesPagerAdapter extends FragmentStateAdapter {

    private int length;
    private boolean isFirstExperience;

    public GuidesPagerAdapter(FragmentActivity activity, int length, boolean isFirstExperience) {
        super(activity);
        this.length = length;
        this.isFirstExperience = isFirstExperience;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new GuideFragment(position);
    }

    @NonNull
    @Override
    public int getItemCount() {
        return length + (isFirstExperience ? 2 : 1);
    }

}
