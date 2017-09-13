package com.adnanyunus.share2grade;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adnan Yunus on 9/5/2017.
 */

public class GradeAnalysisAdapter extends ArrayAdapter {

    private Activity context;
    private List<String> items;

    public GradeAnalysisAdapter(Activity context, List<String> items) {
        super(context, R.layout.grade_analysis_row_layout, items);
        this.context = context;
        this.items = items;
    }

    private static class GradesContainer {
        public TextView item;
        public TextView grade;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        GradesContainer holder = new GradesContainer();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grade_analysis_row_layout, null);
            holder.item = (TextView) v.findViewById(R.id.textView9);
            holder.grade = (TextView) v.findViewById(R.id.textView10);
        } else {
            holder = (GradesContainer) v.getTag();
        }
        final String itemplusgrade = items.get(position);
        String[] itemplusgradestrings = itemplusgrade.split("\t");
        if(itemplusgradestrings[0] != null && !itemplusgradestrings[0].isEmpty())
            holder.item.setText(itemplusgradestrings[0]);
        if(itemplusgradestrings[1] != null && !itemplusgradestrings[1].isEmpty())
            holder.grade.setText(itemplusgradestrings[1]);
        return v;
    }
}

