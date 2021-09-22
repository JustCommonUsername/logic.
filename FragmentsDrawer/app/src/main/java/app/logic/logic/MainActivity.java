package app.logic.logic;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import app.logic.logic.R;
import app.logic.logic.databinding.ActivityMainBinding;
import app.logic.logic.models.CalculatorViewModel;
import app.logic.logic.services.KeyboardInputService;
import app.logic.logic.ui.home.calculator.CalculatorFragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        NavController.OnDestinationChangedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    private MutableLiveData<Integer> currentDestination = new MutableLiveData<>();

    // Make sure, that object below is LinearLayout/CoordinatorLayout/RelativeLayout in order to perform cast without errors
    private RelativeLayout content_main;
    private DrawerLayout drawer;
    private CollapsingToolbarLayout layout;

    private BroadcastReceiver receiver;

    private CalculatorViewModel viewModel;
    private ActivityMainBinding binding;

    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Implementing usage of Data Binding Library (<layout></layout> tags in layout correspond to it) **/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        toolbar = binding.appBarMain.toolbarConsistent;
        setSupportActionBar(toolbar);

        // Getting current activity View Model
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);

        layout = binding.appBarMain.toolbarCollapsing;
        content_main = binding.appBarMain.contentMainCoordinator;
        fab = binding.appBarMain.actionFromSolutions;

        FloatingActionButton close_drawer = binding.navView.getHeaderView(0).findViewById(R.id.close_drawer_action);

        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_about_us, R.id.nav_request, R.id.nav_home_solution)
                .setDrawerLayout(drawer)
                .build();
        NavigationUI.setupWithNavController(layout, toolbar, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        try {
            currentDestination.setValue(navController.getCurrentDestination().getId());
        } catch (NullPointerException e) {
            throw new NullPointerException("NavController produced null ID");
        }

        binding.appBarMain.setDestination(currentDestination);

        // Select the first item of the drawer on the start of application
        navigationView.getMenu().getItem(0).setChecked(true);

        // Handle changing the destinations via navigating
        navController.addOnDestinationChangedListener(this);

        // Handle changing checked menu items
        navigationView.setNavigationItemSelectedListener(this);

        // Handle the appearance of the hamburger icon on the toolbar
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        } catch (NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Syncing drawer bar with application interactions
        initDrawerBar();

        // Setting listener on FloatingActionButton to perform back navigation
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!navController.popBackStack())
                    navController.navigate(R.id.nav_home);
            }
        });

        // Adding a listener for closing event of a drawer Floating Action Button
        try {
            close_drawer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.close();
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final int value = intent.getIntExtra(KeyboardInputService.INFO, 0);

                if (value == CalculatorFragment.HIDE_KEYBOARD || value == Keyboard.KEYCODE_CANCEL)
                    if (getApplicationContext() != null)
                        hideKeyboardFrom(getApplicationContext(), binding.getRoot());
            }
        };

        registerReceiver(receiver, new IntentFilter(KeyboardInputService.CHANNEL));

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.toolbar_consistent_guide) {
                    Intent i = new Intent(MainActivity.this, GuideActivity.class);
                    startActivity(i);
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        item = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (navController.getCurrentDestination().getId() == R.id.nav_home_solution)
            return false;
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        NavigationUI.onNavDestinationSelected(menuItem, navController);
        return true;
    }

    protected void initDrawerBar() {
        // Handle opening of the drawer and sliding content
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                float slideX = drawerView.getWidth() * slideOffset;
                content_main.setTranslationX(slideX);
            }

        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            private boolean isKeyboardClosed = false;

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                if (slideOffset > 0 && !isKeyboardClosed)
                    sendBroadcast(new Intent(KeyboardInputService.CHANNEL).putExtra(KeyboardInputService.INFO, CalculatorFragment.HIDE_KEYBOARD));

                isKeyboardClosed = true;
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller,
                                     @NonNull NavDestination destination, @Nullable Bundle arguments) {
        // Handling change of the destinations and removing drawer from solution fragment
        switch (destination.getId()) {
            case R.id.nav_about_us:
                setDarkMode();
                break;
            case R.id.nav_home_solution:
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                fab.setVisibility(View.VISIBLE);
                fab.animate().scaleX(1.0f).alpha(1.0f).translationZ(10).setInterpolator(new AccelerateDecelerateInterpolator()).start();

                setDarkMode();
                toolbar.setNavigationIcon(null);

                break;
            default:
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                content_main.setBackgroundColor(getResources().getColor(R.color.primaryColor));

                fab.animate().scaleX(0.0f).alpha(0.0f).translationZ(0).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                fab.setVisibility(View.GONE);

                toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay);
                layout.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                layout.setExpandedTitleColor(getResources().getColor(R.color.secondaryColor));
        }

        if (item != null) {
            if (destination.getId() != R.id.nav_home)
                item.setVisible(false).setCheckable(false);
            else
                item.setVisible(true).setCheckable(true);
        }
    }

    @Deprecated
    private void setDarkMode() {
        content_main.setBackgroundColor(getResources().getColor(R.color.backgroundSolutionColor));
        toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay_Dark);
        layout.setBackgroundColor(getResources().getColor(R.color.backgroundSolutionColor));
        layout.setExpandedTitleColor(getResources().getColor(R.color.primaryColor));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public static void hideKeyboardFrom(Context context, View root) {
        InputMethodManager IMM = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        IMM.hideSoftInputFromWindow(root.getWindowToken(), 0);
    }

    public static void showKeyboardFrom(Context context, View view) {
        InputMethodManager IMM  = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        IMM.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

}
