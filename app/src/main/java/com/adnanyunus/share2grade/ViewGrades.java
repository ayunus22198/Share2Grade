package com.adnanyunus.share2grade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class ViewGrades extends AppCompatActivity {
    ListView listView;
    ViewGradesAdapter listDataAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    String compareEmail;
    String compareName;
    Map<String, List<String>> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_listview_layout);
        listView = (ListView) findViewById(R.id.gradesonlv);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        compareEmail = user.getEmail();
        if(compareEmail == null && compareEmail.isEmpty())
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "not null", Toast.LENGTH_SHORT).show();
        compareName = user.getDisplayName();

        listDataAdapter = new ViewGradesAdapter(getApplicationContext(), R.layout.your_list_view_row_layout);
        listView.setAdapter(listDataAdapter);
        String s = "";
        map = new HashMap<>();
        myRef.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child: children)
                {
                    EnhancedEachItemHolder e = child.getValue(EnhancedEachItemHolder.class);
                    ViewGradesProvider vP;
                    if(inMap(map, e.getEmailofgrader(), e.getTitle()))
                        continue;
                    if(e.getEmailofowner() != null && !e.getEmailofowner().isEmpty() && e.getEmailofowner().equals(compareEmail))
                    {
                        vP = new ViewGradesProvider(e.getTitle(),e.getEmailofgrader(), e.getEmailofowner(), e.getItem(), e.isChecked() + "");
                        listDataAdapter.add(vP);
                    }
                    else
                        continue;
                    List<String> values = map.get(e.getEmailofgrader());
                    if(values == null)
                    {
                        values = new ArrayList<>();
                    }
                    values.add(e.getTitle());
                    map.put(e.getEmailofgrader(), values);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public boolean inMap(Map<String, List<String>> map, String key, String value) {
        final List<String> values = map.get(key);
        return values != null && values.contains(value);
    }
}
