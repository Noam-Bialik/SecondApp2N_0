package com.example.secondapp2n_0.ui.FriendsParcels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendsParcelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FriendsParcelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is FriendsParcels fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}