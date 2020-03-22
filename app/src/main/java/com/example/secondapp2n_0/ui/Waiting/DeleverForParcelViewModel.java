package com.example.secondapp2n_0.ui.Waiting;

import android.app.Application;
import android.content.Context;

import androidx.constraintlayout.solver.Cache;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public class DeleverForParcelViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private IParcelsRepository parcelsRepository;
    private Context context;
    public DeleverForParcelViewModel(Context context1) {
      //  super((Application) context1);
        context=context1;
        mText = new MutableLiveData<>();
        mText.setValue("This is RegisteredParcels fragment");
        parcelsRepository= ParcelsRepository.getInstance(context);
        ParcelsRepository.setUser("nave");
        parcelsRepository.setRadiusAndUsername();
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