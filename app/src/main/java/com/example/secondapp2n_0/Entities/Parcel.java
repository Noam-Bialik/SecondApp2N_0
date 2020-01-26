package com.example.secondapp2n_0.Entities;


import android.location.Location;

import com.example.secondapp2n_0.Utils.Converters;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Parcel {
    private int ID;
    private String warehouseID;
    private String warehouseLocation;
    private Enumes.ParcelStatus parcelStatus;
    private Enumes.ParcelType parcelType;
    private Boolean breakable;
    private Enumes.Weight weight;
    private String toName;
    private Location toLocation;
    private String toPhoneNumber;
    private String toMail;
    private SimpleDateFormat sendDate;
    private SimpleDateFormat reciviedDate;
    private String deliverName;
    private HashMap<String, Boolean> availableDeliveries;





    public Parcel() {
    }
    public Parcel(FireParcel parcel) {
        this.ID = parcel.getID();
        this.warehouseID = parcel.getWarehouseID();
        this.warehouseLocation =parcel.getWarehouseLocation() ;
        this.parcelStatus = parcel.getParcelStatus();
        this.parcelType = parcel.getParcelType();
        this.breakable = parcel.getBreakable();
        this.weight = parcel.getWeight();
        this.toName = parcel.getToName();
        this.toLocation = new Location("");
        this.toLocation.setLatitude(parcel.getLa());
        this.toLocation.setLongitude(parcel.getLo());
        this.toPhoneNumber = parcel.getToPhoneNumber();
        this.toMail = parcel.getToMail();
        Converters a = new Converters();

        this.sendDate = a.dateFromDatabase( parcel.getSendDate());
        this.reciviedDate =a.dateFromDatabase( parcel.getReciviedDate());
        this.deliverName = parcel.getDeliverName();
    }

    public Parcel(Enumes.ParcelType parcelType, Boolean breakable, Enumes.Weight weight, String warehouseLocation, String toName, Location toLocation, String toPhoneNumber, String toMail, SimpleDateFormat sendDate, SimpleDateFormat reciviedDate, Enumes.ParcelStatus parcelStatus, String deliverName, String warehouseID) {
        this.parcelType = parcelType;
        this.breakable = breakable;
        this.weight = weight;
        this.warehouseLocation = warehouseLocation;
        this.toName = toName;
        this.toLocation = toLocation;
        this.toPhoneNumber = toPhoneNumber;
        this.toMail = toMail;
        this.sendDate = sendDate;
        this.reciviedDate = reciviedDate;
        this.parcelStatus = parcelStatus;
        this.deliverName = deliverName;
        this.warehouseID = warehouseID;
    }

    public int getID() {
        return ID;
    }



    public void setID(int ID) {
        this.ID = ID;
    }

    public Enumes.ParcelType getParcelType() {
        return parcelType;
    }

    public void setParcelType(Enumes.ParcelType parcelType) {
        this.parcelType = parcelType;
    }

    public Boolean getBreakable() {
        return breakable;
    }

    public void setBreakable(Boolean breakable) {
        this.breakable = breakable;
    }

    public Enumes.Weight getWeight() {
        return weight;
    }

    public void setWeight(Enumes.Weight weight) {
        this.weight = weight;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public SimpleDateFormat getSendDate() {
        return sendDate;
    }

    public void setSendDate(SimpleDateFormat sendDate) {
        this.sendDate = sendDate;
    }

    public SimpleDateFormat getReciviedDate() {
        return reciviedDate;
    }

    public void setReciviedDate(SimpleDateFormat reciviedDate) {
        this.reciviedDate = reciviedDate;
    }

    public Enumes.ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(Enumes.ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(String idStorage) {
        this.warehouseID = idStorage;
    }
    public HashMap<String, Boolean> getAvailableDeliveries() {
        return availableDeliveries;
    }

    public void setAvailableDeliveries(HashMap<String, Boolean> availableDeliveries) {
        this.availableDeliveries = availableDeliveries;
    }

}
