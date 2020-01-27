package com.example.secondapp2n_0.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Data.IParcelsDateSource.DeliveryCallBacks;
import com.example.secondapp2n_0.Data.IParcelsDateSource.OwnerCallBacks;
import com.example.secondapp2n_0.Utils.General;

import java.util.ArrayList;
import java.util.List;

public class ParcelsRepository implements IParcelsRepository {

    private static ParcelsRepository instance = null;
    private IParcelsDateSource parcelsDateSource;
    private static double Radius;
    private MutableLiveData<ArrayList<Parcel>>ownerParcels = new MutableLiveData<ArrayList<Parcel>>();
    private MutableLiveData<ArrayList<Parcel>>deliveryParcels = new MutableLiveData<ArrayList<Parcel>>();
    private static String userName;



    public static ParcelsRepository getInstance(){
        if(instance == null)
            instance = new ParcelsRepository();
        return instance;
    }

    private ParcelsRepository(){
        parcelsDateSource=ParcelDataSource.getInstance();

        parcelsDateSource.notifyToRepository(userName,Radius,new OwnerCallBacks()
        {
            @Override
            public void parcelAdded(Parcel parcel) {
                ArrayList<Parcel> list = ownerParcels.getValue();
                if(list == null)
                    list = new ArrayList<>();
                ownerParcels.setValue(list);
            }

            @Override
            public void parcelRemoved(Parcel parcel) {
                ArrayList<Parcel> list = ownerParcels.getValue();
                if(list == null)
                    list = new ArrayList<>();
                else list.remove(parcel);
                ownerParcels.setValue(list);
            }

            @Override
            public void parcelChanged(Parcel parcel) {
                ArrayList<Parcel> list = ownerParcels.getValue();
                if(list == null)
                    list = new ArrayList<>();
                else {
                    for(Parcel parcel1: list)
                    {
                        if (parcel1.getID()==parcel.getID())
                            list.remove(parcel);
                        list.add(parcel1);
                    }
                }
                ownerParcels.setValue(list);
            }
            },new DeliveryCallBacks() {
            @Override
            public void parcelAdded(Parcel parcel) {
                ArrayList<Parcel> list = deliveryParcels.getValue();
                if(list == null)
                    list = new ArrayList<>();
                deliveryParcels.setValue(list);
            }

            @Override
            public void parcelRemoved(Parcel parcel) {
                ArrayList<Parcel> list = deliveryParcels.getValue();
                if(list == null)
                    list = new ArrayList<>();
                else list.remove(parcel);
                deliveryParcels.setValue(list);
            }

            @Override
            public void parcelChanged(Parcel parcel) {
                ArrayList<Parcel> list = deliveryParcels.getValue();
                if(list == null)
                    list = new ArrayList<>();
                else {
                    for(Parcel parcel1: list)
                    {
                        if (parcel1.getID()==parcel.getID())
                            list.remove(parcel);
                        list.add(parcel1);
                    }
                }
                deliveryParcels.setValue(list);
            }
        });
    }

    @Override
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForOwner() {
        return ownerParcels;
    }

    @Override
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery() throws Exception {
        return deliveryParcels;
    }

    @Override
    public boolean setParcelFromDelivery(Parcel parcel) throws Exception {
        if (parcelsDataSource.updateParcel(parcel))
        {return true;}
        return false;
    }

    @Override
    public boolean setParcelFromOwner(Parcel parcel) throws Exception {
        if (parcelsDataSource.updateParcel(parcel))
        {return true;}
        return false;
    }
}
