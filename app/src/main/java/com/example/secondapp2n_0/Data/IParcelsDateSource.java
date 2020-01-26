package com.example.secondapp2n_0.Data;

import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;

public interface IParcelsDateSource  {

    ArrayList<Parcel> parcels = null;
    /*
     * A - own the parcel
     * B - delivery person
     * */
//dfgjjgjfgjkgdfkg
    //1 The parcels of A that not Arrived
    ArrayList<Parcel> getAllParcelsThatNotArrived(String userName) throws Exception;

    //2 A confirm B
    boolean setConfirmation(Parcel parcel)throws Exception;

    //3 A announced that the parcel arrived
    boolean setParcelArrived(Parcel parcel)throws Exception;

    //4 All the relevant parcels for B
    ArrayList<Parcel> getAllParcelsAvailableInRadius(double radius) throws Exception;

    //5 B announced that he want to take the parcel
    boolean showMeAsDeliveryForTheParcel(Parcel parcel,String userName)throws Exception;
}
