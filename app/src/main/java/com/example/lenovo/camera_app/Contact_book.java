package com.example.lenovo.camera_app;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

public class Contact_book extends ActionBarActivity {

    ListView simpleList;
    String namelist= " ";
    String Phonelist = "";
    String names[];
    String phone_number[];
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    //int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugle, R.drawable.america, R.drawable.new_zealand};
    //String phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_book);
        simpleList = (ListView) findViewById(R.id.simpleListView);
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
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), names);
        simpleList.setAdapter(customAdapter);
    }
}
