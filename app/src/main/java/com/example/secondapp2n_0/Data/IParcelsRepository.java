package com.example.secondapp2n_0.Data;


import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public interface IParcelsRepository {
    IParcelsDateSource parcelsDataSource = null;

    /*
     * A - own the parcels
     * B - delivery person
     * */

    /*
     * 1
     * Get all As parcels.
     * */

    MutableLiveData<ArrayList<Parcel>> getAllParcelsForOwner();
    /*
     * 2
     * Get all Parcels for B according his location.
     * */
    MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery() throws Exception;

    // Maximum we wont use the flowing
    /*
    3
    set parcel after changes from B
     */
    boolean setParcelFromDelivery(Parcel parcel) throws Exception;
    /*
    4
    set parcel after changes from A
     */
    boolean setParcelFromOwner(Parcel parcel) throws Exception;
    void setRadiusAndUsername();
}

