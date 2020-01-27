package com.example.secondapp2n_0.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Enumes;
import com.example.secondapp2n_0.Entities.FireParcel;
import com.example.secondapp2n_0.Entities.Parcel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ParcelDataSource implements IParcelsDateSource {


    private DatabaseReference parcelsRef = FirebaseDatabase.getInstance().getReference("Parcels");
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exex");


    private static ParcelDataSource instance = null;
    private ParcelDataSource(){ }
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
    public void notifyToRepository(final String userName, final double radius, final OwnerCallBacks OwnerCallBacks, final DeliveryCallBacks DeliveryCallBacks) {
        parcelsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FireParcel fireParcel = dataSnapshot.getValue(FireParcel.class);
                Parcel parcel = new Parcel(fireParcel);
                if(matchToOwner(userName,parcel))
                    OwnerCallBacks.parcelAdded(parcel);
                if(matchToDelivery(radius,parcel))
                    DeliveryCallBacks.parcelAdded(parcel);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FireParcel fireParcel = dataSnapshot.getValue(FireParcel.class);
                Parcel parcel = new Parcel(fireParcel);
                if(matchToOwner(userName,parcel))
                    OwnerCallBacks.parcelChanged(parcel);
                if(matchToDelivery(radius,parcel))
                    DeliveryCallBacks.parcelChanged(parcel);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                FireParcel fireParcel = dataSnapshot.getValue(FireParcel.class);
                Parcel parcel = new Parcel(fireParcel);
                if(matchToOwner(userName,parcel))
                    OwnerCallBacks.parcelRemoved(parcel);
                if(matchToDelivery(radius,parcel))
                    DeliveryCallBacks.parcelRemoved(parcel);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private boolean matchToDelivery(double radius, Parcel parcel) {
        return true;
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    private boolean matchToOwner(String userName, Parcel parcel) {
        if(parcel == null ||userName == null)
            return false;
        if(userName.equals( parcel.getToName()) && !(parcel.getParcelStatus().equals(Enumes.ParcelStatus.RECIVED)))
            return true;
        return false;
    }


    @Override
    public boolean updateParcel(Parcel parcel) throws Exception {
        try{
            if(parcel.getAvailableDeliveries()==null)
                parcel.setAvailableDeliveries(new HashMap<String, Boolean>());
            parcelsRef.child(String.valueOf(parcel.getID())).setValue(new FireParcel(parcel));
            return true;
        }
        catch(Exception e){
            throw new Exception(e.getMessage()+"+ ERROR_IN_UPDATEPARCEL");
        }
    }




}
