package com.adnanyunus.share2grade;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class GradedListLayout extends AppCompatActivity {
    Context c;
    TextView gradermesg;
    TextView nameoflist;
    TextView emailofstud;
    ListView listcontentsinhere;
    ArrayAdapter<String> contactAdapter;
    Button gradedsubmit;
    ArrayList<EachItemHolder> eachitemholder;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    FirebaseUser user;
    DatabaseReference myRef;
    String emailref;
    int counter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_list_layout);
        c = this;
        gradermesg = (TextView) findViewById(R.id.Greetings);
        nameoflist = (TextView) findViewById(R.id.textView4);
        emailofstud = (TextView) findViewById(R.id.textView5);
        listcontentsinhere = (ListView) findViewById(R.id.listviewofitems);
        gradedsubmit = (Button) findViewById(R.id.sendgrade);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Items");
        FirebaseUser user = mAuth.getCurrentUser();
        emailref = user.getEmail();
        Bundle bundle = getIntent().getExtras();
        final String title = bundle.getString("titleofthelist");
        String email = bundle.getString("email");
        String listcont = bundle.getString("list");
        gradermesg.setText("Congratulations! You're officially a grader! Here's the gradesheet!");
        emailofstud.setText(email);
        nameoflist.setText(title);
        eachitemholder = new ArrayList<>();
        String[] listcontarr = listcont.split("\n");
        for(int i = 0;i<listcontarr.length;i++)
        {
            EachItemHolder e = new EachItemHolder(listcontarr[i],user.getEmail(), false);
            eachitemholder.add(e);
        }
        GradedListAdapter adapter = new GradedListAdapter(GradedListLayout.this, eachitemholder);
        listcontentsinhere.setAdapter(adapter);
        gradedsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < eachitemholder.size(); i++) {
                    insertData(title, emailref, emailofstud.getText().toString(), eachitemholder.get(i).getItem(), eachitemholder.get(i).isChecked());
                }
                Toast.makeText(c, "Your graded submission was sent!" + "\n" +  "Don't hit the grade button multiple times or else your receiver will be inundated with grades!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void insertData(String title, String emailofgrader, String emailofowner, String item, boolean checked) {
        String id = myRef.push().getKey();
        EnhancedEachItemHolder e = new EnhancedEachItemHolder(title, emailofgrader, emailofowner, item, checked);
        myRef.child(id).setValue(e);


    }
}


