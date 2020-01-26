package com.example.secondapp2n_0.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ParcelDataSource implements IParcelsDateSource {

    private DatabaseReference parcelsRef = FirebaseDatabase.getInstance().getReference("Parcels");
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exex");


    private static ParcelDataSource instance = null;
    private ParcelDataSource(){
        reference.setValue("hi firebase,ParcelDataSource works ");
    }
    public static IParcelsDateSource getInstance(){
        if(instance == null)
            instance = new ParcelDataSource();
        return instance;
    }




    @Override
    public ArrayList<Parcel> getAllParcelsThatNotArrived(String userName) throws Exception {
        return null;
    }
    @Override
    public boolean setConfirmation(Parcel parcel) throws Exception {
        return false;
    }
    @Override
    public boolean setParcelArrived(Parcel parcel) throws Exception {
        return false;
    }
    @Override
    public ArrayList<Parcel> getAllParcelsAvailableInRadius(double radius) throws Exception {
        return null;
    }
    @Override
    public boolean showMeAsDeliveryForTheParcel(Parcel parcel, String userName) throws Exception {
        return false;
    }

    @Override
    public void notifyToOwner(String userName,OwnerCallBacks callBacks) {

    }

    @Override
    public void notifyToDelivery(double radius,DeliveryCallBacks callBacks) {

    }

    @Override
    public boolean updateParcel(Parcel parcel) throws Exception {
        try{

            return true;
        }
        catch(Exception e){
            throw new Exception(e.getMessage()+"+ ERROR_IN_UPDATEPARCEL");
        }
    }

    private void connectToFirebase(){
        parcelsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void sendAddedToOwnerOrDelivery(Parcel parcel){
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    private void sendRemovedToOwnerOrDelivery(Parcel parcel){
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    private void sendChangedToOwnerOrDelivery(Parcel parcel){
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
}
