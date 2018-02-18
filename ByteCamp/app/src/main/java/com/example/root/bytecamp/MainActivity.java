package com.example.root.bytecamp;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    TextView textdate, textheartbeat, texttime;
    private static final String TAG = "MainActivity";

    int sampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textdate = findViewById(R.id.firebase_date);
        textheartbeat = findViewById(R.id.firebase_heartbeat);
        texttime = findViewById(R.id.firebase_time);
        mDatabase = FirebaseDatabase.getInstance().getReference("/users");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d(TAG,dataSnapshot+"");
//             text=findViewById(R.id.firebase_text);
//         //       text.setText(dataSnapshot.getValue()+"");
//        /*        try {
//                    JSONObject root = new JSONObject(dataSnapshot.toString());
//                    String pulse = root.optString("pulse");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//*/     /* sampleData =  Integer.parseInt(dataSnapshot.getValue(String.class));
//
//        if (sampleData >= 100) {
//            text.setText("Your pulse rate is " + sampleData + " which is high");
//        }
//        else if(sampleData<=60){
//            text.setText("Your pulse rate is " + sampleData + " which is low");
//        }
//        else{
//            text.setText("Your pulse rate is " + sampleData + " which is normal");
//        }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });*/


        mDatabase.child("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    textdate.setText(dataSnapshot.getValue()+"");
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("heartbeat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textheartbeat.setText(dataSnapshot.getValue()+"");
                new Notification()
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                texttime.setText(dataSnapshot.getValue()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }


}
