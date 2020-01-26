package com.example.secondapp2n_0.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;
import java.util.List;

public class ParcelsRepository implements IParcelsRepository {

    private static ParcelsRepository instance = null;
    public static ParcelsRepository getInstance(){
        if(instance == null)
            instance = new ParcelsRepository();
        return instance;
    }
    private ParcelsRepository(){

    }

    @Override
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForOwner() {
        return null;
    }

    @Override
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery() {
        return null;
    }

    @Override
    public boolean setParcelFromDelivery(Parcel parcel) {
        return false;
    }

    @Override
    public boolean setParcelFromOwner(List<Parcel> list) {
        return false;
    }
}
