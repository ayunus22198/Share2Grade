package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/8/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Adnan Yunus on 7/19/2017.
 */

public class ListManager extends AppCompatActivity {
    EditText items;
    TextView listcontents;
    int i = 1;
    ArrayList<String> arrlistcon = new ArrayList<String>();

    Context context;
    SQLiteDatabase sqLiteDatabase;
    Button buttonSave;
    TextView titleOfList;
    Context c;
    private String TITLE;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String userID;
    private String email;
    boolean clicked = false;
    DatabaseReference dataBaseLists;
    TextView iddisplayer;
    TextView idlook;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        Bundle bundle = getIntent().getExtras();
        c = this;
        dataBaseLists = FirebaseDatabase.getInstance().getReference("Lists");
        TITLE = bundle.getString("TITLE");
        iddisplayer = (TextView) findViewById(R.id.iddisplayer);
        idlook = (TextView) findViewById(R.id.textView3);
        titleOfList = (TextView) findViewById(R.id.titleOfList);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonShare = (Button) findViewById(R.id.buttonShare);
        buttonSave = (Button) findViewById(R.id.buttonOverwrite);
        Button buttonUndo = (Button) findViewById(R.id.buttonUndo);
        context = this;
        items = (EditText) findViewById(R.id.items);
        listcontents = (TextView) findViewById(R.id.listcontents);
        listcontents.setText("");
        iddisplayer.setText("List ID:");
        listcontents.setMovementMethod(new ScrollingMovementMethod());
        titleOfList.setText(TITLE);
        items.setText("");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        email = user.getEmail();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(context, "Signed In With: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Not Signed In", Toast.LENGTH_SHORT).show();
                }
            }
        };
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = i + ". " + items.getText().toString() + "\n";
                listcontents.append(item);
                arrlistcon.add(item);
                i++;
                items.setText("");
                //Toast.makeText(getApplicationContext(), arrlistcon.size() + " is the size", Toast.LENGTH_SHORT).show();
            }
        });
        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrlistcon.size() > 0) {
                    String deleted = arrlistcon.get(arrlistcon.size() - 1);
                    //Toast.makeText(getApplicationContext(),deleted , Toast.LENGTH_SHORT).show();
                    int beg = listcontents.getText().toString().lastIndexOf(deleted);
                    //Toast.makeText(getApplicationContext(), listcontents.getText().toString().substring(0,beg), Toast.LENGTH_SHORT).show();
                    listcontents.setText(listcontents.getText().toString().substring(0, beg));
                    arrlistcon.remove(arrlistcon.size() - 1);
                    if (i > -1)
                        i--;
                    if (i < 0)
                        i = 0;
                } else {

                }
            }

        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveList();
                clicked = true;
            }
        });
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == false) {
                    Toast.makeText(c, "Make sure you save your list before you share!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent act = new Intent(context, FireBaseDisplay.class);
                    act.putExtra("titleoflist", TITLE);
                    act.putExtra("emailoflistcreator", email);
                    act.putExtra("whateverisinsidethelist", listcontents.getText().toString());
                    act.putExtra("id", idlook.getText().toString().trim());
                    startActivity(act);
                }

            }
        });
    }

    public void saveList() {
        String title = titleOfList.getText().toString().trim();
        String list_contents = listcontents.getText().toString().trim();
        if (list_contents == null || list_contents.length() == 0) {
            Toast.makeText(c, "Please place items in your list!", Toast.LENGTH_SHORT).show();
        } else {
            String id = dataBaseLists.push().getKey();
            idlook.setText(id);
            ListInformations list = new ListInformations(id, title, email, list_contents, "");
            dataBaseLists.child(id).setValue(list);
            Toast.makeText(this, "List Added", Toast.LENGTH_SHORT).show();
        }
    }

   /* public boolean updateListLookers(String id, String title, String owneremail, String list_contents, String newlistlookers) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("lists").child(id);
        ListInformations newList = new ListInformations(title, owneremail, list_contents, newlistlookers);
        Toast.makeText(this, newlistlookers + " can see your list!", Toast.LENGTH_SHORT).show();
        return true;
    }*/
}

