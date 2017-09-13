package com.adnanyunus.share2grade;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class GradeAnalysis extends AppCompatActivity {
    TextView tv;
    ListView lv;
    //DatabaseMainHeadquarters myDB;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    Activity c;
    FirebaseUser user;
    ArrayList<String> List;
    String grader;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_analysis);
        tv = (TextView) findViewById(R.id.textView6);
        lv = (ListView) findViewById(R.id.lvgrades);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        c = this;
        user = mAuth.getCurrentUser();
        Bundle bundle = getIntent().getExtras();
        final String titulo = bundle.getString("title");
        grader = bundle.getString("grader");
        List = new ArrayList<>();
        tv.setText("Congratulations your list got graded by " + grader + ". Look at your results to see how you did!");
        myRef.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child: children)
                {
                    EnhancedEachItemHolder e = child.getValue(EnhancedEachItemHolder.class);
                    EnhancedEachItemHolder ee;
                    if(e.getEmailofowner() != null && !e.getEmailofowner().isEmpty() && e.getEmailofowner().equals(user.getEmail()) && e.getTitle().equals(titulo) && e.getEmailofgrader().equals(grader))
                        ee = new EnhancedEachItemHolder(e.getTitle(), e.getEmailofgrader(), e.getEmailofowner(), e.getItem(), e.isChecked());
                    else
                        continue;
                    List.add(ee.getItem() + "\t" + ee.isChecked());
                }
                GradeAnalysisAdapter gaa = new GradeAnalysisAdapter(c, List);
                lv.setAdapter(gaa);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
}
}
