package com.example.secondapp2n_0.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public interface IParcelsRepository {
    /*
     * A - own the parcels
     * B - delivery person
     * */

    /*
    * 1
    * Get all As parcels.
    * */
    MutableLiveData<ArrayList<Parcel>> getAllMyParcels();
    /*
     * 2
     * Get all Parcels for B according his location.
     * */

}
