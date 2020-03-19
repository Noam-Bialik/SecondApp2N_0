package com.example.secondapp2n_0.Data;



import androidx.lifecycle.MutableLiveData;
import com.example.secondapp2n_0.Data.IParcelsDateSource.OwnerCallBacks;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.Data.IParcelsDateSource.DeliveryCallBacks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ParcelsRepository implements IParcelsRepository {

    private static ParcelsRepository instance = null;
    private IParcelsDateSource parcelsDataSource;
    static String user="נוה";
    static double rad=10;
    private MutableLiveData<ArrayList<Parcel>> ownerParcels = new MutableLiveData<ArrayList<Parcel>>();
    private MutableLiveData<ArrayList<Parcel>>deliveryParcels = new MutableLiveData<ArrayList<Parcel>>();


    //Start with a new radius and userName.
    public static ParcelsRepository getInstance(){
        if(instance == null)
            instance = new ParcelsRepository();

        return instance;
    }

    private ParcelsRepository(){
        parcelsDataSource=ParcelDataSource.getInstance();
    }

    @Override
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForOwner() {

        return ownerParcels;
    }

    @Override
    public MutableLiveData<ArrayList<Parcel>> getAllParcelsForDelivery() throws Exception {
        return deliveryParcels;
    }

    public void setRadiusAndUsername() {
        parcelsDataSource.notifyToRepository(getRad(),getUser(),
                new OwnerCallBacks() {
                    @Override
                    public void parcelAdded(Parcel parcel) {
                        ArrayList<Parcel> list = ownerParcels.getValue();
                        if(list == null)
                            list = new ArrayList<>();
                        else
                        {
                            for (Parcel parcel1:list) {
                                if (parcel1.getID()==parcel.getID())
                                {
                                    return;
                                }
                            }
                        }
                        list.add(parcel);
                        ownerParcels.setValue(list);
                    }

                    @Override
                    public void parcelRemoved(Parcel parcel) {
                        ArrayList<Parcel> list = ownerParcels.getValue();
                        if(list == null)
                            list = new ArrayList<>();
                        else for(Parcel parcel1: new ArrayList<Parcel>(list) {})
                            {
                                if (parcel1.getID()==parcel.getID())
                                    list.remove(parcel1);

                            }
                        ownerParcels.setValue(list);
                    }

                    @Override
                    public void parcelChanged(Parcel parcel) {
                        ArrayList<Parcel> list = ownerParcels.getValue();
                        if(list == null)
                            list = new ArrayList<>();
                        else {
                            for(Parcel parcel1: new ArrayList<Parcel>(list) {})
                            {
                                if (parcel1.getID()==parcel.getID())
                                    list.remove(parcel1);

                            }
                        }
                        if(parcelsDataSource.matchToOwner(user,parcel))
                        {
                            list.add(parcel);
                            ownerParcels.setValue(list);
                        }
                        else ownerParcels.setValue(list);


                    }
                },
                new DeliveryCallBacks(){
                    @Override
                    public void parcelAdded(Parcel parcel) {
                        ArrayList<Parcel> list = deliveryParcels.getValue();
                        if(list == null)
                            list = new ArrayList<>();
                        else
                        {
                            for (Parcel parcel1:list) {
                                if (parcel1.getID()==parcel.getID())
                                {
                                    return;
                                }
                            }
                        }
                        list.add(parcel);
                        deliveryParcels.setValue(list);
                    }

                    @Override
                    public void parcelRemoved(Parcel parcel) {
                        ArrayList<Parcel> list = deliveryParcels.getValue();
                        if(list == null)
                            list = new ArrayList<>();
                        else for(Parcel parcel1: new ArrayList<Parcel>(list) {})
                        {
                            if (parcel1.getID()==parcel.getID())
                                list.remove(parcel1);

                        }
                        deliveryParcels.setValue(list);
                    }

                    @Override
                    public void parcelChanged(Parcel parcel) {
                        ArrayList<Parcel> list = deliveryParcels.getValue();
                        if(list == null)
                            list = new ArrayList<>();
                        else {
                            for(Parcel parcel1: new ArrayList<Parcel>(list) {})
                            {
                                if (parcel1.getID()==parcel.getID())
                                    list.remove(parcel1);
                            }
                        }
                        if(parcelsDataSource.matchToDelivery(rad,parcel))
                        {
                            list.add(parcel);
                            deliveryParcels.setValue(list);
                        }
                        else   deliveryParcels.setValue(list);

                    }
                });

    }

    public static String getUser() {return user; }
    public static double getRad() {
        return rad;
    }
    public static void setUser(String User) { user=User; }
    public static void setRad(double Rad)
    {
        rad=Rad;
    }


    //not using:
    @Override
    public boolean setParcelFromDelivery(Parcel parcel) throws Exception {
        parcelsDataSource=ParcelDataSource.getInstance();
        if (parcelsDataSource.updateParcel(parcel))
        {return true;}
        return false;
    }

    @Override
    public boolean setParcelFromOwner(Parcel parcel) throws Exception {
        parcelsDataSource=ParcelDataSource.getInstance();
        if (parcelsDataSource.updateParcel(parcel))
        {return true;}
        return false;
    }

}
