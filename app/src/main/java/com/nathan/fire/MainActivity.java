package com.nathan.fire;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    Button mBtnSave , mBtnView;
    EditText mUsername, mfullname,mEmail, mPassword;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        grab these Button and EditTExt using their IDs
        mBtnSave = findViewById(R.id.btnsignup);
        mBtnView = findViewById(R.id.btnuser);

        mUsername = findViewById(R.id.edtusername);
        mfullname = findViewById(R.id.edtfullnames);
        mEmail = findViewById(R.id.edtemail);
        mPassword = findViewById(R.id.edtpassword);


        dialog = new ProgressDialog(this);
        dialog.setTitle("Saving...");
        dialog.setMessage("Please wait as we save data");

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                grab data the user has entered
                String yusername = mUsername.getText().toString();
                String yfullnames = mfullname.getText().toString();
                String yemail = mEmail.getText().toString();
                String ypassword = mPassword.getText().toString().trim();

//                we need an id to store data to the db tables columns
                long time = System.currentTimeMillis();
//                Because columns can only store  strings, convert time to strings

                String timeConverted = String.valueOf(time);

//                validate and check if the user entered all data required

                if(yusername.isEmpty() || yfullnames.isEmpty() || yemail.isEmpty() || ypassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fill all inputs", Toast.LENGTH_SHORT).show();
                }else{
//                    proceed to save data to the db table
//                    first start by creating a child/table

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/"+timeConverted);
                    Item x = new Item (yusername,yfullnames,yemail,ypassword,timeConverted);

//                    push data to the db
                    dialog.show();
                    ref.setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "User saved successfully", Toast.LENGTH_SHORT).show();
                                mUsername.setText("");
                                mfullname.setText("");
                                mEmail.setText("");
                                mPassword.setText("");
                            }else{
                                Toast.makeText(MainActivity.this, "Saving failed", Toast.LENGTH_SHORT).show();
                            };
                        }
                    });

                }

            }
        });
        mBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to UserActivity
                startActivity(new Intent("android.intent.action.UserActivity"));
            }
        });
    }
}
