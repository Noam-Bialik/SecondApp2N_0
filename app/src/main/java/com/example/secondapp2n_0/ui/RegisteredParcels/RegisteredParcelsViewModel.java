package com.example.secondapp2n_0.ui.RegisteredParcels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisteredParcelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegisteredParcelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is RegisteredParcels fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}