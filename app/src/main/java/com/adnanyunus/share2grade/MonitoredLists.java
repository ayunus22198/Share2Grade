package com.adnanyunus.share2grade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class MonitoredLists extends AppCompatActivity {
    ListView listv;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    MonitoredListAdapter listDataAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_monitored_list);
        listv = (ListView) findViewById(R.id.alllistsonalistview);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        listDataAdapter = new MonitoredListAdapter(getApplicationContext(), R.layout.items_in_monitored_list);
        listv.setAdapter(listDataAdapter);
        databaseReference.child("Lists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child: children)
                {
                    ListInformations listinfo = child.getValue(ListInformations.class);
                    ListInformations li;
                    if(listinfo.getPeoplewhocanseelist() != null && !listinfo.getPeoplewhocanseelist().isEmpty() && user.getEmail() != null && !user.getEmail().isEmpty() && listinfo.getPeoplewhocanseelist().contains(user.getEmail()))
                        li = new ListInformations(listinfo.getId(), listinfo.getTitle(), listinfo.getEmail(), listinfo.getWhatisinsidethelist(), listinfo.getPeoplewhocanseelist());
                    else
                        continue;
                    listDataAdapter.add(li);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
}
}
