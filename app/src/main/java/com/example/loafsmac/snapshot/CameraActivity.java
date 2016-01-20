package com.example.loafsmac.snapshot;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.*;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

public class CameraActivity extends ActionBarActivity {

    private static final int CAMERA_ID = 1;
    private Uri imageUri;
    private ImageView imageView;
    private EditText editText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void takePhoto(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult( cameraIntent, CAMERA_ID );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                //imageView.setImageBitmap(bitmap);
//                File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
//                MediaStore.Images.Media.insertImage(CameraActivity.this.getContentResolver(), bitmap,
//                        "Pic" + ".jpg", "photo");
                saveBitmap(bitmap, "sampleFilename");
                Bitmap bitmapToDisplayFromSDCard = BitmapFactory.decodeFile("/sdcard/test.png");
                imageView.setImageBitmap(bitmapToDisplayFromSDCard);
            } catch (Error e) {

                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                        .show();
                Log.e("Camera", e.toString());
            }
        }
    }

    private boolean saveBitmap( Bitmap bitmapInput,String fileName){
        try {
            FileOutputStream stream = new FileOutputStream("/sdcard/" + fileName + "png");
            bitmapInput.compress(Bitmap.CompressFormat.PNG, 40, stream);
            return true;

        }
        catch(FileNotFoundException e){
            System.out.print(e);
            return false;
        }
/* Write bitmap to file using JPEG or PNG and 80% quality hint for JPEG. */

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        System.out.print("------------------------------------------------------");
        imageView = (ImageView) findViewById(R.id.ImageView);
        //Date now = DateFormat.getDateTimeInstance();
        takePhoto(imageView);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        String momentString = "Naruto will be Hokage, Believe it!";
        myDatabase.execSQL("insert into moments(image, text) VALUES(" "+ momentString + ");");

    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Camera Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.loafsmac.snapshot/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Camera Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.loafsmac.snapshot/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }
}
