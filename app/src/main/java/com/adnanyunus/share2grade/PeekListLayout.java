package com.adnanyunus.share2grade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class PeekListLayout extends AppCompatActivity {
    TextView gradermesg;
    TextView nameoflist;
    TextView emailofstud;
    TextView listcontentsinhere;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peek_list_layout);
        gradermesg = (TextView) findViewById(R.id.greeting);
        nameoflist = (TextView) findViewById(R.id.identitiyoflist);
        emailofstud = (TextView) findViewById(R.id.emailofpersoyouregrading);
        listcontentsinhere = (TextView) findViewById(R.id.listcontentsinhere);
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("titleofthelist");
        String email = bundle.getString("email");
        String listcont = bundle.getString("list");
        gradermesg.setText("Congratulations! You're officially a grader! Here's a peek!");
        emailofstud.setText(email);
        listcontentsinhere.setText(listcont);
        nameoflist.setText(title);
    }
}
