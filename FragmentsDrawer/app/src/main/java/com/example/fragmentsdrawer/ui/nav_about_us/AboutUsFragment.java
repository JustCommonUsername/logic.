package com.example.fragmentsdrawer.ui.nav_about_us;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fragmentsdrawer.R;

import java.net.URI;
import java.net.URISyntaxException;

public class AboutUsFragment extends Fragment {

    private TextView openSourceDescription;
    private ImageButton toGitHub, toVK;
    private NavController controller;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        openSourceDescription = view.findViewById(R.id.open_source_description);

        controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        toGitHub = view.findViewById(R.id.github_link);
        toVK = view.findViewById(R.id.vk_link);

        SpannableString spans = new SpannableString(openSourceDescription.getText().toString());

        spans.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.primaryOutlineDescription)),
                17,
                openSourceDescription.getText().toString().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spans.setSpan(
                new StyleSpan(Typeface.BOLD),
                17,
                openSourceDescription.getText().toString().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        openSourceDescription.setText(spans);

        toGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/JustCommonUsername"));
                startActivity(browserIntent);
            }
        });

        toVK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/yanfarba"));
                startActivity(browserIntent);
            }
        });
    }

}
