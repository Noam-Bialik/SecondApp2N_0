package com.example.secondapp2n_0.ui.WaitingForTaking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public class WaitingForTakingViewModel extends ViewModel {

    private IParcelsRepository parcelsRepository;

    public WaitingForTakingViewModel() {
    }
}