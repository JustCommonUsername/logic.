package app.logic.logic.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import app.logic.logic.MainActivity;
import app.logic.logic.R;
import app.logic.logic.adapters.CollectionPagerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private CollapsingToolbarLayout toolbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        CollectionPagerAdapter adapter = new CollectionPagerAdapter(this);
        ViewPager2 pager = (ViewPager2)view.findViewById(R.id.pages);

        pager.setAdapter(adapter);
        toolbar = getActivity().findViewById(R.id.toolbar_collapsing);

        TabLayout tabs = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabs, pager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0:
                                tab.setIcon(R.drawable.ic_calculator_primary_light_24dp);
                                break;
                            case 1:
                                tab.setIcon(R.drawable.ic_history_primary_light_24dp);
                                break;
                            default:
                                Toast.makeText(getActivity().getApplicationContext(), "Недоступная в данный момент страница", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).attach();

        if (toolbar != null)
            pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

                private int previousPosition = 0;

                @Override
                public void onPageSelected(final int position) {
                    MainActivity.hideKeyboardFrom(getActivity().getApplicationContext(), HomeFragment.this.getView().getRootView());

                    if (previousPosition != position) {
                        switch (position) {
                            case 0:
                                toolbar.setTitle(getString(R.string.home_calculator));
                                break;
                            case 1:
                                toolbar.setTitle(getString(R.string.home_history));
                                break;
                        }

                        previousPosition = position;
                    }
                }
            });
        else
            Snackbar.make(view, "To better experience, reload an app", Snackbar.LENGTH_SHORT)
                    .setTextColor(getResources().getColor(R.color.primaryColor))
                    .show();
    }

}
