package com.adnanyunus.share2grade;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class UpdateList extends AppCompatActivity {
    EditText ed;
    Button adding;
    Button clearing;
    TextView tv;
    Button sv;
    String oldText;
    String iD;
    String title;
    String graders;
    Context context = this;
    FirebaseUser user;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_list_layout);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ed = (EditText) findViewById(R.id.entry);
        adding = (Button) findViewById(R.id.add);
        clearing = (Button) findViewById(R.id.clr);
        tv = (TextView) findViewById(R.id.updatedlist);
        sv = (Button) findViewById(R.id.buttonOverwrite);
        myRef = FirebaseDatabase.getInstance().getReference();
        Bundle extras = getIntent().getExtras();
        oldText = extras.getString("data");
        iD = extras.getString("id");
        title = extras.getString("title");
        graders = extras.getString("graders");
        tv.setText(oldText);
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = ed.getText().toString();
                result = result + "\n";
                tv.append(result);
                ed.setText("");
            }
        });
        clearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
            }
        });
        sv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String list_contents = tv.getText().toString().trim();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Lists").child(iD);
                ListInformations listInformations = new ListInformations(iD, title, user.getEmail(), list_contents, graders);
                databaseReference.setValue(listInformations);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
