package com.example.secondapp2n_0.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;
import java.util.List;

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
    MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery();

    // Maximum we wont use the flowing
    /*
    3
    set parcel after changes from B
     */
    boolean setParcelFromDelivery(Parcel parcel);
    /*
    4
    set parcel after changes from A
     */
    boolean setParcelFromOwner(List<Parcel> list);


}
