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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class PreviousListsAdapter extends ArrayAdapter {
    //UpdateList u
    List list = new ArrayList();
    public PreviousListsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView ListContents;
        TextView pplthatcansee;
        Button delete;
        Button update;

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
    //public void deleteinAdap()
    public void hitme(View v)
    {
        //     Intent i = new Intent( ArrayAdapter.class , UpdateList.class);
        //   x.getApplicationContext().startActivity(i);

    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler = new LayoutHandler();

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.your_list_view_row_layout, null);
            layoutHandler.ListContents = (TextView) row.findViewById (R.id.nameList);
            layoutHandler.pplthatcansee = (TextView) row.findViewById(R.id.textView8);
            layoutHandler.delete = (Button) row.findViewById(R.id.buttonDelete);
            layoutHandler.update = (Button) row.findViewById(R.id.buttonUpdateyours);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        final ListInformations listProvider = (ListInformations) this.getItem(position);
        layoutHandler.ListContents.setText(listProvider.getTitle());
        layoutHandler.pplthatcansee.setText(listProvider.getId());
        layoutHandler.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatabaseReference delList = FirebaseDatabase.getInstance().getReference("Lists").child(listProvider.getId());
                delList.removeValue();
                list.remove(listProvider);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();


            }
        });
        layoutHandler.update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent act = new Intent(getContext(), UpdateList.class);
                act.putExtra("data", listProvider.getWhatisinsidethelist());
                act.putExtra("id",listProvider.getId());
                act.putExtra("title", listProvider.getTitle());
                act.putExtra("graders", listProvider.getPeoplewhocanseelist());
                getContext().startActivity(act);
            }
        });

        return row;
    }
}

