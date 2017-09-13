package com.adnanyunus.share2grade;
/**
 * Created by Adnan Yunus on 9/10/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
import java.util.List;

/**
 * Created by Adnan Yunus on 7/27/2017.
 */
public class FireBaseDisplay extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabse;
    FirebaseUser user;
    DatabaseReference myRef;
    ListView listView;
    Button shareusers;
    List<UserInformations> usersI;
    Context c;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_user_wants_to_share);
        c = this;
        myRef = FirebaseDatabase.getInstance().getReference("Accounts");
        listView = (ListView) findViewById(R.id.listview_that_user_picks_to_share);
        shareusers = (Button) findViewById(R.id.buttonsharewithdesiredusers);
        usersI = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        final String titleofthelistthatwillbeshared = bundle.getString("titleoflist");
        final String emailoftheuser_listthatwillbeshared = bundle.getString("emailoflistcreator");
        final String whatsunderthelist = bundle.getString("whateverisinsidethelist");
        final String listid = bundle.getString("id");
        shareusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "";
                for (int i = 0; i < usersI.size(); i++) {
                    if (usersI.get(i).isSelected() && !s.contains(usersI.get(i).getAddressSaved())) {
                        s = s + usersI.get(i).getAddressSaved() + " ";
                    }
                }
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                boolean success = updateListLookers(listid, titleofthelistthatwillbeshared, emailoftheuser_listthatwillbeshared, whatsunderthelist, s);
                if(success)
                    Toast.makeText(getBaseContext(), s + " can see your list!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public boolean updateListLookers(String id, String title, String owneremail, String list_contents, String newlistlookers) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Lists").child(id);
        ListInformations newList = new ListInformations(id, title, owneremail, list_contents, newlistlookers);
        databaseReference.setValue(newList);
        Toast.makeText(this, newlistlookers + " can see your list!", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                usersI.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    UserInformations userI = ds.getValue(UserInformations.class);
                    usersI.add(userI);
                }
                FireBaseDisplayAdapter adapter = new FireBaseDisplayAdapter(FireBaseDisplay.this, usersI);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        int pos = listView.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION)
        {
            UserInformations u = usersI.get(pos);
            if(isChecked) {
                u.setSelected(true);
                Toast.makeText(getApplicationContext(), "Clicked on " + u.getAddressSaved(), Toast.LENGTH_SHORT).show();
            }
            if(!isChecked)
            {
                u.setSelected(false);
                Toast.makeText(getApplicationContext(), "Un-Clicked on " + u.getAddressSaved(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}

