package app.logic.logic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import app.logic.logic.R;
import app.logic.logic.util.SolutionInfo;

import java.util.ArrayList;

public class SolutionCardsAdapter extends ArrayAdapter<SolutionInfo> {

    public SolutionCardsAdapter(Context context, ArrayList<SolutionInfo> list) {
        super(context, R.layout.solution_item_card, list);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        SolutionInfo info = getItem(position);

        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.solution_item_card, parent, false);

        ((TextView)view.findViewById(R.id.solution_item_name)).setText(info.getType());
        ((TextView)view.findViewById(R.id.solution_item_verbose)).setText(info.getFunction());

        return view;
    }

}
