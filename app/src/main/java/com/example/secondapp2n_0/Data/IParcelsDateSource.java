package com.example.secondapp2n_0.Data;

import com.example.secondapp2n_0.Entities.Parcel;

import java.util.ArrayList;
import java.util.List;

public interface IParcelsDateSource  {


    /*
     * A - own the parcel
     * B - delivery person
     * */

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


    //we use:
    void notifyToRepository(String userName,double radius,final OwnerCallBacks OwnerCallBacks ,final DeliveryCallBacks DeliveryCallBacks);


    //listeners
    public interface OwnerCallBacks{
        void parcelAdded(Parcel parcel);
        void parcelRemoved(Parcel parcel);
        void parcelChanged(Parcel parcel);
    }
    //listeners
    public interface DeliveryCallBacks{
        void parcelAdded(Parcel parcel);
        void parcelRemoved(Parcel parcel);
        void parcelChanged(Parcel parcel);
    }

    /*
       update parcel to DataBase
        */
    boolean updateParcel(Parcel parcel) throws Exception;

}
