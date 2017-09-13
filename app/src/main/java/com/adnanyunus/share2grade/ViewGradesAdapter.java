package com.adnanyunus.share2grade;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class ViewGradesAdapter extends ArrayAdapter {
    List list = new ArrayList();


    public ViewGradesAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
    static class LayoutHandler
    {
        TextView text;
        Button viewgrade;
    }
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }
    public int getCount() {
        return list.size();
    }
    public Object getItem(int position) {
        return list.get(position);
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler = new LayoutHandler();

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.feedback_listview_row_layout, null);
            layoutHandler.text = (TextView) row.findViewById(R.id.textView3);
            layoutHandler.viewgrade = (Button) row.findViewById(R.id.buttonseegrade);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        final ViewGradesProvider listProvider = (ViewGradesProvider) this.getItem(position);
        layoutHandler.text.setText(listProvider.getTitle() + " is graded by \n" + listProvider.getEmail());
        layoutHandler.viewgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(getContext(), GradeAnalysis.class);
                act.putExtra("title", listProvider.getTitle());
                act.putExtra("grader", listProvider.getEmail());
                getContext().startActivity(act);
            }
        });

        return row;
    }
}
