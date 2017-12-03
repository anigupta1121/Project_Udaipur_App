package com.project_udaipur;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyService extends Service {

    DatabaseReference mDatabase;
    private int mNotificationId = 001;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
        return START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child("Data");
        final Intent intent = new Intent("data");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("ALERT!!")
                                .setVibrate(new long[]{500, 500})
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setContentText("Something is wrong at the location!");
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                intent.putExtra("age",dataSnapshot.child("age").getValue().toString());
                intent.putExtra("gender",dataSnapshot.child("gender").getValue().toString());
                intent.putExtra("happiness",dataSnapshot.child("emotions").child("happiness").getValue().toString());
                intent.putExtra("neutral",dataSnapshot.child("emotions").child("neutral").getValue().toString());
                intent.putExtra("sadness",dataSnapshot.child("emotions").child("sadness").getValue().toString());


                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                        new Intent(getApplication(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);




                mBuilder.setContentIntent(contentIntent);


                mNotifyMgr.notify(mNotificationId, mBuilder.build());

                sendBroadcast(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
    }
