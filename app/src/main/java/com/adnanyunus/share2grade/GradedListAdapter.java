package com.adnanyunus.share2grade;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class GradedListAdapter extends ArrayAdapter {
    private Activity context;
    private List<EachItemHolder> items;

    public GradedListAdapter(Activity context, List<EachItemHolder> items) {
        super(context, R.layout.items_to_be_graded_row_layout, items);
        this.context = context;
        this.items = items;
    }

    private static class FireBaseDisplayHolder {
        public TextView item;
        public RadioGroup rg;
        public RadioButton rb1;
        public RadioButton rb2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        FireBaseDisplayHolder holder = new FireBaseDisplayHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.items_to_be_graded_row_layout, null);
            holder.item = (TextView) v.findViewById(R.id.textView7);
            holder.rg = (RadioGroup) v.findViewById(R.id.group);
            holder.rb1 = (RadioButton) v.findViewById(R.id.radioButton2);
            holder.rb2 = (RadioButton) v.findViewById(R.id.radioButton3);
            //holder.cb1.setOnCheckedChangeListener();
        } else {
            holder = (FireBaseDisplayHolder) v.getTag();
        }
        final EachItemHolder iteminlist = items.get(position);
        holder.item.setText(iteminlist.getItem());
        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                switch(rb.getId())
                {
                    case R.id.radioButton2:
                        iteminlist.setChecked(true);
                        Toast.makeText(getContext(), iteminlist.getItem() + " is true", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton3:
                        iteminlist.setChecked(false);
                        Toast.makeText(getContext(), iteminlist.getItem() + " is false", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return v;
    }
}

