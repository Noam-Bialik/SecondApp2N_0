package com.example.secondapp2n_0.Utils;

import android.location.Location;

import com.example.secondapp2n_0.Entities.Enumes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Converters {

    public SimpleDateFormat dateFromDatabase(String date) {
        return date == null ? null : new SimpleDateFormat(date);
    }

    public String dateToDatabase(SimpleDateFormat date) {
        if (date != null)
        {
            Calendar cal = Calendar.getInstance();
            return date.format(cal.getTime());

        }
        return null;
    }

    public String parcelStatusToDatabase(Enumes.ParcelStatus parcelStatus) {
        return parcelStatus == null ? null : parcelStatus.toString();
    }

    public String parcelTypeToDatabase(Enumes.ParcelType parcelType) {
        return parcelType == null ? null : parcelType.toString();
    }

    public String weightToDatabase(Enumes.Weight weight) {
        return weight == null ? null : weight.toString();
    }

    public Enumes.ParcelStatus parcelStatusFromDatabase(String parcelStatus) {
        return parcelStatus == null ? null : Enumes.ParcelStatus.valueOf(parcelStatus);
    }

    public Enumes.ParcelType parcelTypeFromDatabase(String parcelType) {
        return parcelType == null ? null : Enumes.ParcelType.valueOf(parcelType);
    }

    public Enumes.Weight weightFromDatabase(String weight) {
        return weight == null ? null : Enumes.Weight.valueOf(weight);
    }

    public String locationToDatabase(Location location) {
        if (location==null)
            return null;
        String str;
        str=Location.convert(location.getLatitude(),Location.FORMAT_DEGREES)+" "+Location.convert(location.getLongitude(),Location.FORMAT_DEGREES);
        return str;
    }

    public Location locationFromDatabase(String location) {
        if (location==null)
            return null;

        double s1=Double.parseDouble(location.substring(0,location.length()/2));
        double s2=Double.parseDouble(location.substring(location.length()/2,location.length()));
        Location location1=new Location("location");
        location1.setLatitude(s1);
        location1.setLongitude(s2);
        return location1;
    }
}