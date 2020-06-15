package com.example.fragmentsdrawer.ui.nav_send_request;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.rooms.SpreadsheetService;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestFragment extends Fragment {

    private TextInputLayout broad, choosing, email;
    private ExtendedFloatingActionButton action;

    private static final String SCRIPT_URL = "https://script.google.com/macros/s/AKfycbxjPpG69L8QNoap2OdXqiP_pYfbJMqDMTHnVsxnu2-Vf3aMPU0/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.send_request, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle onSavedInstanceState) {
        broad = view.findViewById(R.id.broad_answer_field);
        choosing = view.findViewById(R.id.choosing_answer_field);
        email = view.findViewById(R.id.email_field);

        action = view.findViewById(R.id.send_repair_request_button);

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
                String email = RequestFragment.this.email.getEditText().getText().toString();

                SendInfoRequest task = new SendInfoRequest(email, broad_answer, choosing_answer);
                task.execute();
            }
        });
    }

    private class SendInfoRequest extends AsyncTask<Void, Integer, String> {

        private String broad_answer, choosing_answer, email;
        private static final String SPREADSHEET_ID = "1gushgoxQJ_Q6AWqJMlQxNxclFkXC8M-6DKyAfCKTIwc";
        private AlertDialog dialog;
        private ProgressBar progressBar;
        private TextView status;

        SendInfoRequest(String info, String broad, String choosing) {
            broad_answer = broad;
            choosing_answer = choosing;
            email = info;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            final View viewDialog = getLayoutInflater().inflate(R.layout.loading_dialog, null);

            dialog = new AlertDialog.Builder(getActivity())
                    .setView(viewDialog)
                    .setCancelable(false)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            // TODO: Implement animation
                        }
                    })
                    .create();

            progressBar = viewDialog.findViewById(R.id.request_progress_bar);
            status = viewDialog.findViewById(R.id.request_bar_status);

            progressBar.setIndeterminate(true);
            progressBar.setMax(100);

            dialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // TODO: Update ProgressBar level in UI
            ObjectAnimator.ofInt(progressBar, "progress", values[0])
                    .setDuration(300)
                    .start();
        }

        @Override
        protected String doInBackground(Void... values) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SCRIPT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            SpreadsheetService insertService = retrofit.create(SpreadsheetService.class);
            publishProgress(20);
            Call<String> callback = insertService.insert(email, broad_answer, choosing_answer, new Date().toString(), SPREADSHEET_ID);

            try {
                publishProgress(100);
                return callback.execute().body();
            } catch (IOException e) {
                return "ERR";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO: Show complete in ProgressBar and hide a Dialog View
            switch (result) {
                case "ERR":
                    status.setText("Error");
                default:
                    status.setText("Success!");
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dialog.dismiss();

            try {
                broad.getEditText().setText(null);
                choosing.getEditText().setText(null);
                RequestFragment.this.email.getEditText().setText(null);
            } catch (NullPointerException e) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).popBackStack(R.id.nav_home, false);
            }
        }

    }

}
