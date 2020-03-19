package com.example.secondapp2n_0.ui.FriendsParcels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public class FriendsParcelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private IParcelsRepository parcelsRepository;

    public FriendsParcelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is FriendsParcels fragment");
        parcelsRepository= ParcelsRepository.getInstance();
        ParcelsRepository.setRad(100);
        parcelsRepository.setRadiusAndUsername();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public boolean setParcelFromDelivery(Parcel parcel) throws Exception {
        parcelsRepository= ParcelsRepository.getInstance();


        if (parcelsRepository.setParcelFromDelivery(parcel))
        {return true;}
        return false;
    }
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery() throws Exception {
        return parcelsRepository.getAllParcelsForDelivery();
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