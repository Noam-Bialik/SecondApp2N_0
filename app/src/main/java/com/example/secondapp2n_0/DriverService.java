package com.example.secondapp2n_0;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DriverService extends Service {
    public DriverService() {
    }
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Parcels");
    // Backend_Factory backend_factory=new Backend_Factory();
    final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

    @Override
    public void onCreate() {
        super.onCreate();
        //LoginTime=(System.currentTimeMillis()/1000);
    }

    void sendBroadcastToInformer(){
        Intent intent = new Intent();
        ComponentName cn = new ComponentName("com.e.reciver","com.e.reciver.Reciver");
        intent.setComponent(cn);
        sendBroadcast(intent);
    }
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                sendBroadcastToInformer();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return START_STICKY ;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}