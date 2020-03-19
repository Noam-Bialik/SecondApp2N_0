package com.example.secondapp2n_0.ui.RegisteredParcels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public class DeleverForParcelViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private IParcelsRepository parcelsRepository;
    public DeleverForParcelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is RegisteredParcels fragment");
        parcelsRepository= ParcelsRepository.getInstance();
        ParcelsRepository.setUser("nave");
        parcelsRepository.setRadiusAndUsername();
    }


    public boolean setParcelFromOwner(Parcel parcel) throws Exception {
        parcelsRepository= ParcelsRepository.getInstance();
        if (parcelsRepository.setParcelFromOwner(parcel))
        {return true;}
        return false;
    }
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForOwner() {
        return parcelsRepository.getAllParcelsForOwner();
    }

}