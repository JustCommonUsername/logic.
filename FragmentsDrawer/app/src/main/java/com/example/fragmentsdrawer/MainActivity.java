package com.example.fragmentsdrawer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.fragmentsdrawer.databinding.ActivityMainBinding;
import com.example.fragmentsdrawer.databinding.AppBarMainBinding;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;
import com.example.fragmentsdrawer.services.KeyboardInputService;
import com.example.fragmentsdrawer.ui.home.calculator.CalculatorFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.LinkedList;

import kotlin.jvm.JvmStatic;

import static java.security.AccessController.getContext;

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

    private CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Implementing usage of Data Binding Library (<layout></layout> tags in layout correspond to it) **/
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_about_us, R.id.nav_request)
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
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
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                sendBroadcast(new Intent(KeyboardInputService.CHANNEL).putExtra(KeyboardInputService.INFO, CalculatorFragment.HIDE_KEYBOARD));
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
                toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay_Dark);
                layout.setBackgroundColor(getResources().getColor(R.color.backgroundSolutionColor));
                layout.setExpandedTitleColor(getResources().getColor(R.color.primaryColor));
                break;
            case R.id.nav_home_solution:
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                content_main.setBackgroundColor(getResources().getColor(R.color.backgroundSolutionColor));

                toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay_Dark);
                layout.setBackgroundColor(getResources().getColor(R.color.backgroundSolutionColor));
                layout.setExpandedTitleColor(getResources().getColor(R.color.primaryColor));
                fab.setVisibility(View.VISIBLE);

                try {
                    getActionBar().setDisplayHomeAsUpEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                content_main.setBackgroundColor(getResources().getColor(R.color.primaryColor));

                toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay);
                layout.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                layout.setExpandedTitleColor(getResources().getColor(R.color.secondaryColor));
                fab.setVisibility(View.GONE);
        }
        currentDestination.postValue(destination.getId());
    }

    public static void hideKeyboardFrom(Context context, Fragment fragment) {
        InputMethodManager IMM = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        IMM.hideSoftInputFromWindow(fragment.getView().getRootView().getWindowToken(), 0);
    }

    public static void showKeyboardFrom(Context context, View view) {
        InputMethodManager IMM  = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        IMM.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

}
