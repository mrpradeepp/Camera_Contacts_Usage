package com.example.lenovo.camera_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

/**
 * Created by lenovo on 8/5/2016.
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    String countryList[],key;
    //Cursor result[];
    int flags[],index;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] countryList) {
        this.context = context;
        this.countryList = countryList;

        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        index=i;
        view = inflter.inflate(R.layout.activity_listview, null);
        final TextView country = (TextView) view.findViewById(R.id.caption);
        //ImageView icon = (ImageView) view.findViewById(R.id.icon);
        country.setText(countryList[i]);
        //country.setText(result[i].toString());

        final Button countrybutton=(Button)view.findViewById(R.id.addbutton);
        final Button deletebutton=(Button)view.findViewById(R.id.deletebutton);
        countrybutton.setText("ADD" + countryList[i]);
        //countrybutton.setText(result[i].toString());


        // key=countryList[i];
       // icon.setImageResource(flags[i]);
        countrybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), countrybutton.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(v.getContext(),countrybutton.getText().toString()+"Delete me!!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
