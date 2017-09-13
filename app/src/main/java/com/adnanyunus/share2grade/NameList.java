package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/8/2017.
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

/**
 * Created by Adnan Yunus on 7/19/2017.
 */

public class NameList extends AppCompatActivity{
    //private String title;
    private Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorize_list);
        TextView qAboutName = (TextView) findViewById(R.id.qAboutName);
        final EditText listName = (EditText) findViewById(R.id.listName);
        Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        context = this;
        listName.setText("");
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listName.getText().toString().trim() == null || listName.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "Please give your list a title", Toast.LENGTH_LONG).show();
                } else {
                    String title = listName.getText().toString();
                    Intent intent = new Intent(context, ListManager.class);
                    intent.putExtra("TITLE", title);
                    startActivity(intent);
                }
            }
        });
    }

}
