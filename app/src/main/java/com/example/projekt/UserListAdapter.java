package com.example.projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {

    private Context mContext;
    int mResource;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public UserListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String password = getItem(position).getPassword();
        String country = getItem(position).getCountry();
        String age = getItem(position).getAge();

        User user = new User(name, password, country, age);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tname = (TextView) convertView.findViewById(R.id.textView1);
        TextView tcountry = (TextView) convertView.findViewById(R.id.textView2);
        TextView tage = (TextView) convertView.findViewById(R.id.textView3);

        tname.setText(name);
        tcountry.setText("country: " + country);
        tage.setText("age: " + age);

        return convertView;
    }
}











