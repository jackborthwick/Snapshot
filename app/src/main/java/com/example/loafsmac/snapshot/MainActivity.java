package com.example.loafsmac.snapshot;

import android.app.Activity;
import android.graphics.Camera;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.app.NotificationManager;
import android.app.Notification;
import android.content.res.Resources;
import android.content.Intent;
import android.app.PendingIntent;
public class MainActivity extends ActionBarActivity {
    SQLiteDatabase myDatabase;
    Context context;
    Activity activity;
    Camera camera;

    /**
     * Notification code
     */
    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, CameraActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.camera)
                .setContentTitle("hi")
                .setContentText("hello")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        setContentView(R.layout.activity_main);

        // DATABASE STUFF
        myDatabase = openOrCreateDatabase("picDiaryDB", Context.MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS moments" +
                "(image BLOB, time DATETIME, text TEXT, address TEXT);");
        myDatabase.execSQL("insert into moments(text) VALUES(\"I LOVE CATS\");");
        String testDB = getTableAsString(myDatabase, "moments ");
        System.out.print(testDB);
        showNotification();


    }

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}




