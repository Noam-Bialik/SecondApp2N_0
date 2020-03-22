package com.example.secondapp2n_0.ui.AllWaiting;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.Utils.GPService;
import com.example.secondapp2n_0.Utils.LocationTrack;

import java.util.ArrayList;

public class AllWaitingViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private IParcelsRepository parcelsRepository;
    private Context context;
    //public MutableLiveData<ArrayList<Parcel>> parcels;
    public AllWaitingViewModel(Context context1) {
        context=context1;
        mText = new MutableLiveData<>();
        mText.setValue("This is FriendsParcels fragment");
        parcelsRepository= ParcelsRepository.getInstance(context);
        ParcelsRepository.setRad(100);
        parcelsRepository.setRadiusAndUsername();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public boolean setParcelFromDelivery(Parcel parcel) throws Exception {

        parcelsRepository= ParcelsRepository.getInstance(context);
        if (parcelsRepository.setParcelFromDelivery(parcel))
        {return true;}
        return false;
    }
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery() throws Exception {
       /* parcels=parcelsRepository.getAllParcelsForDelivery();
        ArrayList<Parcel> arrayList=parcels.getValue();
        for (Parcel parcel: arrayList)
        {
            if (ownerTooFarFromDelivery(parcel))
            arrayList.remove(parcel);
        }
        parcels.postValue(arrayList);
        return parcels;*/
       return parcelsRepository.getAllParcelsForDelivery();
    }

    private boolean ownerTooFarFromDelivery(Parcel parcel) {
        Location  location=new Location("");
        try {
            location=GPService.getLocationFromAddress(parcel.getWarehouseLocation(),context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Location location1=myLocation();
        double b=GPService.distance(location1,location)+GPService.distance(location,parcel.getToLocation());
        return b>100;
    }

    private Location myLocation() {
        LocationTrack locationTrack;
        Location location=new Location("");
        locationTrack = new LocationTrack(context);
        location.setLatitude(locationTrack.getLatitude());
        location.setLongitude(locationTrack.getLongitude());
        locationTrack.stopListener();
        return location;
    }

    public static void setUser(String User) { ParcelsRepository.setUser(User); }
    public static void setRad(double Rad)
    {
        ParcelsRepository.setRad(Rad);
    }
    public static String getUser() {return ParcelsRepository.getUser(); }
    public static double getRead() {
        return ParcelsRepository.getRad();
    }
}