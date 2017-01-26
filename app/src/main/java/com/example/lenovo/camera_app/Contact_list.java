package com.example.lenovo.camera_app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Contact_list extends ActionBarActivity {
ListView lv;
String namelist= " ";
String Phonelist = "";
String names[];
String phone_number[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        lv = (ListView) findViewById(R.id.contactlist);
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {

            // REad contact name & number
            String contactname = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phonemumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (contactname != null) {
                namelist += contactname + ",";
                Phonelist += phonemumber + ",";
            }
            // phones.close();

        }
            // convert csv into array

            names =  namelist.split(",");
            phone_number = Phonelist.split(",");

        //Create array adapter for listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String msg = "tel:"+phone_number[position];
                Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse(msg));
                startActivity(callintent);
               // Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            }
        });


    }

}


