package com.example.fragmentsdrawer.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.rooms.Equation;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import kotlin.jvm.JvmStatic;

public class BindingAdapters {

    @JvmStatic
    @BindingAdapter("hideIfZero")
    public static void hideIfZero(View view, Boolean isEmpty) {
        try {
            view.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @JvmStatic
    @BindingAdapter("showAlertCase")
    public static void showAlertCase(View view, Boolean observable) {
        if (observable)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.GONE);
    }

    @JvmStatic
    @BindingAdapter("setCurrentTheme")
    public static void setCurrentTheme(ViewGroup view, LiveData<Integer> id) {
        if (id.getValue() == R.id.nav_home_solution || id.getValue() == R.id.nav_about_us) {
            if (view.getClass() == Toolbar.class) {
                Toolbar toolbar = (Toolbar) view;
                toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay_Dark);
            } else if (view.getClass() == CollapsingToolbarLayout.class) {
                CollapsingToolbarLayout layout = (CollapsingToolbarLayout)view;
                layout.setBackgroundColor(view.getResources().getColor(R.color.backgroundSolutionColor));
                layout.setExpandedTitleColor(view.getResources().getColor(R.color.primaryColor));
            }
        } else {
            if (view.getClass() == CollapsingToolbarLayout.class) {
                CollapsingToolbarLayout layout = (CollapsingToolbarLayout)view;
                layout.setBackgroundColor(view.getResources().getColor(R.color.primaryColor));
                layout.setExpandedTitleColor(view.getResources().getColor(R.color.secondaryColor));
            } else if (view.getClass() == Toolbar.class)
                ((Toolbar)view).setPopupTheme(R.style.AppTheme_PopupOverlay);
        }
    }

    @JvmStatic
    @BindingAdapter("showFabSolution")
    public static void showFabInSolutionFragment(FloatingActionButton fab, LiveData<Integer> id) {
        try {
            if (id.getValue() == R.id.nav_home_solution)
                fab.setVisibility(View.VISIBLE);
            else
                fab.setVisibility(View.GONE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @JvmStatic
    @BindingAdapter("showHistoryPlaceholder")
    public static void showHistoryPlaceholder(View view, List<Equation> list) {
        view.setVisibility(list != null && list.size() > 0 ? View.GONE : View.VISIBLE);
    }

}
