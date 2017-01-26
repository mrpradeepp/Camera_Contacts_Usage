package com.example.lenovo.camera_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.Random;

public class MainActivity extends ActionBarActivity {
File imagefile;
    static Camera camera;
    AutoCompleteTextView country_list;
    String[] countries = {"India","Indonasia","Canada","Mexico","Brazil","Australia"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        country_list =(AutoCompleteTextView)findViewById(R.id.country_text);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,countries);
        country_list.setThreshold(1);
        country_list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void take(View view) {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //mcamera.st
      //  camera = Camera.Open(android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK);
        Random rand = new Random();
        int n = rand.nextInt(20) + 1;

        imagefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"test"+n+".jpg");
       // Toast.makeText(this,"test"+n+".jpg",Toast.LENGTH_LONG).show();
        Uri tempuri= Uri.fromFile(imagefile);
        i.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
        i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(i,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==0)
       {
           switch(requestCode)
           {
               case Activity.RESULT_OK:
                   if(imagefile.exists())
                   {
                       Toast.makeText(this,"File saved in " + imagefile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                       Log.e("Location ",imagefile.getAbsolutePath());

                   }
                   else
                   {
                       Toast.makeText(this,"There was some error in taking image",Toast.LENGTH_LONG).show();
                       Log.e("Location ", imagefile.getAbsolutePath());
                   }

                   break;

               case Activity.RESULT_CANCELED:

                   break;

               default:
                   break;
           }


       }
    }

    public void next(View view) {
        Intent i = new Intent(MainActivity.this,Take_photo.class);
        startActivity(i);
    }

    public void contact(View view) {
        Intent j = new Intent(MainActivity.this,Contact_list.class);
        startActivity(j);

    }

    public void send_mail(View view) {
        Intent intent  = new Intent(MainActivity.this,email_app.class);
        startActivity(intent);
    }

    public void start_contactbook(View view) {
        Intent i = new Intent(MainActivity.this,Contact_book.class);
        startActivity(i);
    }
}
