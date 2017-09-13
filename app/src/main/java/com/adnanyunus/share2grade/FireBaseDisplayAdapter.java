package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/10/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adnan Yunus on 7/27/2017.
 */

public class FireBaseDisplayAdapter extends ArrayAdapter<UserInformations> {
    private Activity context;
    private List<UserInformations> users;

    public FireBaseDisplayAdapter(Activity context, List<UserInformations> users) {
        super(context, R.layout.firebase_accounts_layout, users);
        this.context = context;
        this.users = users;
    }

    private static class FireBaseDisplayHolder {
        public TextView tvName;
        public TextView tvEmail;
        public CheckBox cb;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        FireBaseDisplayHolder holder = new FireBaseDisplayHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.firebase_accounts_layout, null);
            holder.tvName = (TextView) v.findViewById(R.id.firebaseName);
            holder.tvEmail = (TextView) v.findViewById(R.id.fireBaseEmail);
            holder.cb = (CheckBox) v.findViewById(R.id.checkBox);
            holder.cb.setOnCheckedChangeListener((FireBaseDisplay) context );
        } else {
            holder = (FireBaseDisplayHolder) v.getTag();
        }
        UserInformations user = users.get(position);
        holder.tvName.setText(user.getNameSaved());
        holder.tvEmail.setText(user.getAddressSaved());
        return v;
    }
}
