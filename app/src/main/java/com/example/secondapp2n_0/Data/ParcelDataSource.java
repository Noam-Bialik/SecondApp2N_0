package com.example.secondapp2n_0.Data;

import android.content.Context;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.secondapp2n_0.Entities.Enumes;
import com.example.secondapp2n_0.Entities.FireParcel;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.Utils.GPService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;



public class ParcelDataSource implements IParcelsDateSource {


    private static Context context;
    private DatabaseReference parcelsRef = FirebaseDatabase.getInstance().getReference("Parcels");
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exex");

    private static ParcelDataSource instance = null;
    private ParcelDataSource(){ }

    public static IParcelsDateSource getInstance(){
        if(instance == null) {
            instance = new ParcelDataSource();
            //context= context1;
        }
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
    public void notifyToRepository(final double radius, final String userName, final OwnerCallBacks OwnerCallBacks, final DeliveryCallBacks DeliveryCallBacks) {
        parcelsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FireParcel fireParcel = dataSnapshot.getValue(FireParcel.class);
                if(fireParcel.getAvailableDeliveries() == null)
                    fireParcel.setAvailableDeliveries(new HashMap<String, Boolean>());
                Parcel parcel = new Parcel(fireParcel);
                if(matchToOwner(userName,parcel))
                    OwnerCallBacks.parcelAdded(parcel);
                if(matchToDelivery(radius, parcel))
                    DeliveryCallBacks.parcelAdded(parcel);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FireParcel fireParcel = dataSnapshot.getValue(FireParcel.class);
                if(fireParcel.getAvailableDeliveries() == null)
                    fireParcel.setAvailableDeliveries(new HashMap<String, Boolean>());
                Parcel parcel = new Parcel(fireParcel);
                //if(matchToOwner(userName,parcel))
                    OwnerCallBacks.parcelChanged(parcel);
                //if(matchToDelivery(radius ,parcel))
                    DeliveryCallBacks.parcelChanged(parcel);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                FireParcel fireParcel = dataSnapshot.getValue(FireParcel.class);
                if(fireParcel.getAvailableDeliveries() == null)
                    fireParcel.setAvailableDeliveries(new HashMap<String, Boolean>());
                Parcel parcel = new Parcel(fireParcel);
                if(matchToOwner(userName,parcel))
                    OwnerCallBacks.parcelRemoved(parcel);
                if(matchToDelivery(radius, parcel))
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

    public boolean matchToDelivery(double radius, Parcel parcel) {
        HashMap<String, Boolean> availableDeliveries = parcel.getAvailableDeliveries();
        try {
            if(ownerTooFarFromDelivery(radius,parcel))
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(availableDeliveries == null)
            return true;
        /*for(Boolean val :availableDeliveries.values() ){
            if(val == true)
                return false;
        }*/
        return true;
        //!!!!!!!!!!!!!!!!!!!!!!!!!NOT CHECKED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    /**
     * return false if the toLocation close to the Delivery location.
     * @param radius
     * @param parcel
     * @return
     */
    private boolean ownerTooFarFromDelivery(double radius, Parcel parcel) throws Exception {
        //Location location=GPService.getLocationFromAddress(parcel.getWarehouseLocation(),context);
        //return  radius>GPService.distance(myLocation(),location)+GPService.distance(location,parcel.getToLocation());
          return false;

    }
    private Location myLocation()
    {
        return new Location("");
    }

    public boolean matchToOwner(String userName, Parcel parcel) {
        if(parcel == null ||userName == null)
            return false;
        if(userName.equals( parcel.getToName()) && parcel.getParcelStatus().equals(Enumes.ParcelStatus.WAITING))
            return true;
        return false;
    }


    @Override
    public boolean updateParcel(Parcel parcel) throws Exception {
        try{
            if(parcel.getAvailableDeliveries()==null)
                parcel.setAvailableDeliveries(new HashMap<String, Boolean>());
            parcelsRef.child(String.valueOf(parcel.getID())).setValue(new FireParcel(parcel));

        }
        catch(Exception e){
            throw new Exception(e.getMessage()+"+ ERROR_IN_UPDATEPARCEL");
        }
        return true;
    }
}
