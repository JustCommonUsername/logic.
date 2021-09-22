package app.logic.logic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;

import app.logic.logic.R;

import app.logic.logic.adapters.GuidesPagerAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class GuideActivity extends AppCompatActivity
        implements View.OnClickListener {

    public static final String TAG = "IS_ALREADY_EXPERIENCED";

    private Toolbar toolbar;
    private ExtendedFloatingActionButton actionButton;
    private TextView guidance, explanation;
    private ViewPager2 pager;
    private TabLayout tabs;
    private LinearLayout greetingLayout;

    private boolean isFirstExperience;
    private boolean isLastElement = false;
    private boolean isPagerHidden = false;

    private String[] guides;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide_main);

        explanation = findViewById(R.id.guide_view_explanation);
        toolbar = findViewById(R.id.guide_toolbar_constant);
        pager = findViewById(R.id.guide_view_pager);
        tabs = findViewById(R.id.guide_tab_overview);
        guidance = findViewById(R.id.guide_item_text);
        actionButton = findViewById(R.id.guide_pager_action);
        greetingLayout = findViewById(R.id.guide_view_greeting);

        findViewById(R.id.guide_toolbar_layout) /* AppBarLayout*/ .bringToFront();

        isFirstExperience = isFirstExperience();

        setSupportActionBar(toolbar);

        guides = new String[] {
                getString(R.string.guide_instruction_config_of_keyboard),
                getString(R.string.guide_instruction_beginning_of_work),
                getString(R.string.guide_instruction_getting_answer),
                getString(R.string.guide_instruction_getting_result_steps),
                getString(R.string.guide_instruction_getting_equation_history),
                getString(R.string.guide_instruction_extra_contact_information),
                getString(R.string.guide_instruction_sending_request_explanation)
        };

        actionButton.setOnClickListener(this);

        GuidesPagerAdapter adapter = new GuidesPagerAdapter(this, guides.length, isFirstExperience);

        pager.setAdapter(adapter);

        new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(null);
            }
        }).attach();

        guidance.setAlpha(1);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                pager.setCurrentItem(pager.getCurrentItem() + (isLastElement ? 0 : 1), false);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.guide_done_action)
                    onNavigateAction();

                return true;
            }
        });

        if (pager.getAdapter() != null)
            pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

                private float sumPositionAndPositionOffset;

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                    final int swipingDirection = position + positionOffset > sumPositionAndPositionOffset ? 1 : -1;

                    if (guidance.getAlpha() > 0) {
                        guidance.setAlpha((1f - 2 * positionOffset) * swipingDirection);
                    }

                    sumPositionAndPositionOffset = position + positionOffset;
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    if (position == pager.getAdapter().getItemCount() - 1) {

                        actionButton.setIcon(null);
                        actionButton.setText(getString(R.string.guide_action_done));
                        isLastElement = true;

                        guidance.setText(isFirstExperience ? null : guides[position - 1]);

                        // Hiding the toolbar because of last page
                        if (!isPagerHidden && isFirstExperience) {
                            pager.animate().alpha(0).setDuration(1000).start();
                            greetingLayout.animate().alpha(1).setDuration(1000).start();
                            toolbar.setVisibility(View.GONE);
                            isPagerHidden = true;
                        }

                        if (explanation.getAlpha() > 0)
                            explanation.animate().alpha(0).setDuration(1000).start();
                    }
                    else if (position == 0) {

                        guidance.setText(null);
                        explanation.animate().alpha(1).setDuration(1000).start();
                    }
                    else {

                        // Getting main tutorial information
                        // Reducing by 1 in case there is extra page in the beginning
                        // TODO: Clarify, if the first page is needed
                        guidance.setText(guides[position - 1]);

                        if (guidance.getAlpha() < 1)
                            guidance.animate().alpha(1).setDuration(1000).start();

                        actionButton.setIcon(getDrawable(R.drawable.ic_guide_action_12dp));
                        actionButton.setText(getString(R.string.guide_action_next));
                        isLastElement = false;

                        if (isPagerHidden && isFirstExperience) {
                            greetingLayout.animate().alpha(0).setDuration(1000).start();
                            pager.animate().alpha(1).setDuration(1000).start();
                            toolbar.setVisibility(View.VISIBLE);
                            isPagerHidden = false;
                        }

                        if (explanation.getAlpha() > 0)
                            explanation.animate().alpha(0).setDuration(1000).start();
                    }
                }

            });
    }

    private boolean isFirstExperience() {
        return !PreferenceManager.getDefaultSharedPreferences(this).getBoolean(TAG, false);
    }

    private void onNavigateAction() {
        if (isFirstExperience) {
            Intent i = new Intent(GuideActivity.this, MainActivity.class);

            // Applying flag to identify, that the user has already visited the app
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(TAG, true).apply();

            startActivity(i);
            finish();
        } else
            finishAfterTransition();
    }

    @Override
    public void onClick(View v) {
        if (isLastElement) {
            onNavigateAction();
        } else if (pager.getAdapter() != null) {
            if (pager.getCurrentItem() < pager.getAdapter().getItemCount() - 1)
                pager.setCurrentItem(pager.getCurrentItem() + 1, false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.guide_toolbar_options, menu);
        return true;
    }

}
