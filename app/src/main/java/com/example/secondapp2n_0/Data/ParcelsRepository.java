package com.example.secondapp2n_0.Data;



import android.content.Context;
import android.location.Location;

import androidx.lifecycle.MutableLiveData;
import com.example.secondapp2n_0.Data.IParcelsDateSource.OwnerCallBacks;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.Data.IParcelsDateSource.DeliveryCallBacks;
import com.example.secondapp2n_0.Utils.GPService;
import com.example.secondapp2n_0.Utils.LocationTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ParcelsRepository implements IParcelsRepository {

    private static ParcelsRepository instance = null;
    private IParcelsDateSource parcelsDataSource;
    static String user="";
    static double rad=10;
    private MutableLiveData<ArrayList<Parcel>> ownerParcels = new MutableLiveData<ArrayList<Parcel>>();
    private MutableLiveData<ArrayList<Parcel>>deliveryParcels = new MutableLiveData<ArrayList<Parcel>>();
    private static Context context;


    //Start with a new radius and userName.
    public static ParcelsRepository getInstance(Context context1){
        if(instance == null)
        {
            instance = new ParcelsRepository();
            context= context1;
        }

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
                        if(parcelsDataSource.matchToOwner(user,parcel))
                        {
                            list.add(parcel);
                            ownerParcels.setValue(list);
                        }
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
                        //ownerTooFarFromDelivery(parcel)
                        if(true)
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
                        //&&!ownerTooFarFromDelivery(parcel)
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

    private boolean ownerTooFarFromDelivery(Parcel parcel) {
        Location location=new Location("");
        try {
            location= GPService.getLocationFromAddress(parcel.getWarehouseLocation(),context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Location location1=myLocation();
        double b=GPService.distance(location1,location)+GPService.distance(location,parcel.getToLocation());
        return b>100;
    }

    private Location myLocation() {
        LocationTrack locationTrack;
        Location location=new Location("");
        locationTrack = new LocationTrack(context);
        location.setLatitude(locationTrack.getLatitude());
        location.setLongitude(locationTrack.getLongitude());
        locationTrack.stopListener();
        return location;
    }

}
