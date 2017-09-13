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
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class MonitoredListAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public MonitoredListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
    static class LayoutHandler2
    {
        TextView titleOfList;
        Button peek;
        Button grade;
    }
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }
    public int getCount()
    {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler2 layoutHandler = new LayoutHandler2();

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.items_in_monitored_list, null);
            layoutHandler.titleOfList = (TextView) row.findViewById (R.id.NameOfTheList);
            layoutHandler.peek = (Button) row.findViewById(R.id.buttonPeek);
            layoutHandler.grade = (Button) row.findViewById(R.id.buttonGrade);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler2) row.getTag();
        }
        final ListInformations mProvider = (ListInformations) this.getItem(position);
        layoutHandler.titleOfList.setText(mProvider.getTitle());
        layoutHandler.grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent act = new Intent(getContext(), GradedListLayout.class);
                    act.putExtra("titleofthelist", mProvider.getTitle());
                    act.putExtra("email", mProvider.getEmail());
                    act.putExtra("list", mProvider.getWhatisinsidethelist());
                    getContext().startActivity(act);
                } catch(Exception e)
                {
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    Toast.makeText(getContext(), "Error: " + errors.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        layoutHandler.peek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(getContext(), PeekListLayout.class);
                act.putExtra("titleofthelist", mProvider.getTitle());
                act.putExtra("email", mProvider.getEmail());
                act.putExtra("list", mProvider.getWhatisinsidethelist());
                getContext().startActivity(act);
            }
        });


        return row;
    }
}
