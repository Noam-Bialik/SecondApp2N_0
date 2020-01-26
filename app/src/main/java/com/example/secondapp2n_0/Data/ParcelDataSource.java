package com.example.secondapp2n_0.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;
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
        return false;
    }
}
