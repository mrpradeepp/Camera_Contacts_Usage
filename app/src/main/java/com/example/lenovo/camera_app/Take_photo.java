package com.example.lenovo.camera_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.Random;

public class Take_photo extends ActionBarActivity {

    Button clickbutton;
    ImageView photo;
    File imgfile;
    public static final  int REQUEST_CAPTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        clickbutton = (Button)findViewById(R.id.btn_photo);
        photo = (ImageView)findViewById(R.id.photoview);
        if(!isCamera())
        {

            clickbutton.setEnabled(false);
        }
    }
    public boolean isCamera()
    {

        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


    public void start_capture(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Random rand = new Random();
        int n = rand.nextInt(20) + 1;

       imgfile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"test1.jpg");
        Uri tempuri= Uri.fromFile(imgfile);
        //i.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
        i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap bit = (Bitmap) extras.get("data");
            photo.setImageBitmap(bit);
        }
    }
}
