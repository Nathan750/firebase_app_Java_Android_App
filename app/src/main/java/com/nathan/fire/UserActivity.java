package com.nathan.fire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Item> itemList;
    CustomAdapter customAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, itemList);
        listView.setAdapter(customAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    itemList.add(item);
                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item selectedItem = itemList.get(position);
                Intent intent = new Intent(UserActivity.this, DetailActivity.class);
                intent.putExtra("username", selectedItem.getUsername());
                intent.putExtra("fullnames", selectedItem.getFullnames());
                intent.putExtra("email", selectedItem.getEmail());
                intent.putExtra("password", selectedItem.getPassword());
                startActivity(intent);
            }
        });
    }
}
