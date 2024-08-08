package com.nathan.fire;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Item> data;

    public CustomAdapter(Context mContext, ArrayList<Item> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.musername = convertView.findViewById(R.id.tvuname);
            viewHolder.mfullnames = convertView.findViewById(R.id.tvfullname);
            viewHolder.memail = convertView.findViewById(R.id.tvemail);
            viewHolder.mpassword = convertView.findViewById(R.id.tvpassword);
            viewHolder.mbtndelete = convertView.findViewById(R.id.btndelete);
            viewHolder.mbtnview = convertView.findViewById(R.id.btnview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Item person = data.get(position);
        viewHolder.musername.setText(person.getUsername());
        viewHolder.mfullnames.setText(person.getFullnames());
        viewHolder.memail.setText(person.getEmail());
        viewHolder.mpassword.setText(person.getPassword());

        viewHolder.mbtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/" + person.getId());
                ref.removeValue();
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.mbtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("username", person.getUsername());
                intent.putExtra("fullnames", person.getFullnames());
                intent.putExtra("email", person.getEmail());
                intent.putExtra("password", person.getPassword());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView musername;
        TextView mfullnames;
        TextView memail;
        TextView mpassword;
        Button mbtndelete;
        Button mbtnview;
    }
}
