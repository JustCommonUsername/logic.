package com.example.fragmentsdrawer.ui.nav_send_request;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragmentsdrawer.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class RequestFragment extends Fragment {

    private TextInputLayout broad, choosing;
    private ExtendedFloatingActionButton action;
    private Drawable progressBarDrawable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.send_request, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle onSavedInstanceState) {
        broad = view.findViewById(R.id.broad_answer_field);
        choosing = view.findViewById(R.id.choosing_answer_field);
        action = view.findViewById(R.id.send_repair_request_button);

        progressBarDrawable = new ProgressBar(getContext()).getProgressDrawable();

        String[] places = new String[] {
                getResources().getString(R.string.home_calculator),
                getResources().getString(R.string.menu_about_us),
                getResources().getString(R.string.menu_send_request),
                getResources().getString(R.string.home_history),
                getResources().getString(R.string.home_solution)
        };

        AutoCompleteTextView autocomplete = (AutoCompleteTextView)choosing.getEditText();
        autocomplete.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.request_field_choosing, places));

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String broad_answer = broad.getEditText().getText().toString();
                String choosing_answer = choosing.getEditText().getText().toString();

                new SendInfoRequest(broad_answer, choosing_answer).execute();
            }
        });
    }

    private class SendInfoRequest extends AsyncTask<String, Integer, Void> {

        private String broad_answer, choosing_answer;

        SendInfoRequest(String broad, String choosing) {
            broad_answer = broad;
            choosing_answer = choosing;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if (values[0] == 0)
                action.setIcon(progressBarDrawable);
        }

        @Override
        protected Void doInBackground(String... values) {
            for (int i = 0; i < 5; i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            action.setIcon(getResources().getDrawable(R.drawable.ic_send_black_48dp));
        }

    }

}
