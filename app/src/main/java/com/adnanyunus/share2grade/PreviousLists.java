package com.adnanyunus.share2grade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan Yunus on 9/10/2017.
 */

public class PreviousLists extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private ListView lv;
    private PreviousListsAdapter listDataAdapter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_list_view);
        lv = (ListView) findViewById(R.id.old_list_collection);
        mAuth = FirebaseAuth.getInstance();
        final List<ListInformations> collection = new ArrayList<ListInformations>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        listDataAdapter = new PreviousListsAdapter(getApplicationContext(), R.layout.your_list_view_row_layout);
        lv.setAdapter(listDataAdapter);
        databaseReference.child("Lists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child: children)
                {
                    ListInformations listinfo = child.getValue(ListInformations.class);
                    ListInformations li;
                    if(mAuth.getCurrentUser().getEmail().equals(listinfo.getEmail()))
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
