package com.adnanyunus.share2grade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreen extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;
    TextView welcomeScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Button buttonCreate = (Button) findViewById(R.id.buttonCreate);
        Button buttonMon = (Button) findViewById(R.id.button3);
        Button buttonUserLists = (Button) findViewById(R.id.buttonUserLists);
        Button buttonFed = (Button) findViewById(R.id.buttonFeedback);
        Button buttonLogout = (Button) findViewById(R.id.buttonLogOut);
        welcomeScreen = (TextView) findViewById(R.id.textViewWelcome);
        welcomeScreen.setText("Welcome to Share2Grade!");
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, NameList.class);
                startActivity(intent);
            }
        });

        buttonUserLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, PreviousLists.class);
                startActivity(intent);
            }
        });

        buttonMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(MainScreen.this, MonitoredLists.class);
                startActivity(act);
            }
        });
        buttonFed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(MainScreen.this, ViewGrades.class);
                startActivity(act);
            }
        });
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainScreen.this, LoginActivity.class));
            }
        });
    }

}
