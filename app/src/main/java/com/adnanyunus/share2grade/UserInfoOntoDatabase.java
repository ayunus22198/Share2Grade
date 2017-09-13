package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/10/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Adnan Yunus on 7/24/2017.
 */

public class UserInfoOntoDatabase extends AppCompatActivity{
    TextView hey;
    TextView thanks;
    TextView knowmore;
    TextView namethatwillbesaved;
    EditText promptedname;
    TextView emailthatwillbesaved;
    EditText promptedemail;
    Button finalized;
    Context context;
    //private Firebase mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabse;
    private DatabaseReference myRef;

    ArrayList<UserInformations> users =new ArrayList<>();
    //private DatabaseReference databaseReference;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_more_about_user);
        context = this;
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        Bundle bundle = getIntent().getExtras();
        String prevemail = bundle.getString("email");
        String prevname = bundle.getString("name");
        hey = (TextView) findViewById(R.id.HeyText);
        thanks = (TextView) findViewById(R.id.ThanksText);
        knowmore = (TextView) findViewById(R.id.KnowMoreText);
        namethatwillbesaved = (TextView) findViewById(R.id.nameText);
        promptedname = (EditText) findViewById(R.id.promptName);
        emailthatwillbesaved = (TextView) findViewById(R.id.emailText);
        promptedemail = (EditText) findViewById(R.id.promptEmail);
        finalized = (Button) findViewById(R.id.finalized);
        promptedemail.setText(prevemail);
        promptedname.setText(prevname);
        finalized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(promptedemail.getText().toString().trim().length() == 0 || promptedname.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "This isn't allowed, try again", Toast.LENGTH_LONG).show();
                }
                else{
                    saveUserInformation();

                }
            }
        });
    }
    private void saveUserInformation()
    {
        String nameFinal = promptedname.getText().toString().trim();
        String emailFinal = promptedemail.getText().toString().trim();
        UserInformations userInformation = new UserInformations(nameFinal, emailFinal);
        FirebaseUser user = mAuth.getCurrentUser();
        myRef.child("Accounts").push().setValue(userInformation);
        Toast.makeText(this, "Info Saved", Toast.LENGTH_LONG).show();
        Intent act = new Intent(context, LoginActivity.class);
        startActivity(act);
    }
    public ArrayList<UserInformations> retrieve()
    {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return users;
    }
    private void fetchData(DataSnapshot dataSnapshot)
    {
        users.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            UserInformations userInformations=ds.getValue(UserInformations.class);
            users.add(userInformations);
        }
    }
    public void onBackPressed() {
    }

}
