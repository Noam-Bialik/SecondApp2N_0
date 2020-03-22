package com.example.secondapp2n_0.ui.Waiting;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public class WaitingViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private IParcelsRepository parcelsRepository;
    private Context context;
    public WaitingViewModel(Context context1,String userName) {
        //super((Application) context1);
        context=context1;
        mText = new MutableLiveData<>();
        mText.setValue("This is RegisteredParcels fragment");
        parcelsRepository= ParcelsRepository.getInstance(context);
        ParcelsRepository.setUser(userName);
        parcelsRepository.setRadiusAndUsername();
    }

    public LiveData<String> getText() {
        return mText;
    }


    public boolean setParcelFromOwner(Parcel parcel) throws Exception {
        parcelsRepository= ParcelsRepository.getInstance(context);
        if (parcelsRepository.setParcelFromOwner(parcel))
        {return true;}
        return false;
    }
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForOwner() {
        return parcelsRepository.getAllParcelsForOwner();
    }


}