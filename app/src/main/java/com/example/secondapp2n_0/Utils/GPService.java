package com.example.secondapp2n_0.Utils;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.lang.Math;

public class GPService {


    public static String getCompleteAddress(Location location, Context context) {
        String locationstr = "";
        try {
            //  Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            Geocoder geocoder = new Geocoder(context,  new Locale("he"));
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String state, city, zip, street;
                if (address.getAdminArea() != null) {
                    state = address.getAdminArea();
                } else {
                    state = "";
                }
                if (address.getLocality() != null) {
                    city = address.getLocality();
                } else {
                    city = "";
                }
                if (address.getPostalCode() != null) {
                    zip = address.getPostalCode();
                } else {
                    zip = "";
                }

                if (address.getThoroughfare() != null) {
                    street = address.getSubLocality() + "," + address.getThoroughfare();
                } else {
                    street = address.getSubLocality() + "," + address.getFeatureName();
                }
                locationstr = street + "," + city + "," + zip + "," + state;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locationstr;
    }

    public static Location getLocationFromAddress(String strAddress , Context context) throws Exception {

        //    Geocoder coder = new Geocoder(this);
        Geocoder coder = new Geocoder(context,  new Locale("he"));
        List<Address> address;

            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Location object = new Location("service Provider");
            object.setLongitude(lng);
            object.setLatitude(lat);
            return object;
    }

    public static double distance(Location location1,Location location2)
    {
        return Math.sqrt(Math.pow(location1.getLatitude()-location2.getLatitude(),2)+Math.pow(location1.getLongitude()-location2.getLongitude(),2));
    }
}
