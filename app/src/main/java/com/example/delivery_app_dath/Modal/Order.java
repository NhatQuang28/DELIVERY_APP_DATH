package com.example.delivery_app_dath.Modal;

import org.parceler.Parcel;

@Parcel
public class Order {
    //latitude(vĩ độ) longitude(tung độ)
    private double longitudeRecive;
    private double latitudeRecive;
    private double longitudePickup;
    private double latitudePickup;
    private double distance;
    private double unitPrice;
    private String idShiper;
    private String vehicle;
    private String discountCode;
    private boolean confirmPickup = false;
    private boolean confirmPayment = false;
    private boolean confirmComplete = false;
    private String createDay;
    private String phoneNumber;
    private String note;

    public Order(double longitudeRecive, double latitudeRecive, double longitudePickup, double latitudePickup, double distance, double unitPrice, String idShiper, String vehicle, String discountCode, boolean confirmPickup, boolean confirmPayment, boolean confirmComplete, String createDay, String phoneNumber, String note) {
        this.longitudeRecive = longitudeRecive;
        this.latitudeRecive = latitudeRecive;
        this.longitudePickup = longitudePickup;
        this.latitudePickup = latitudePickup;
        this.distance = distance;
        this.unitPrice = unitPrice;
        this.idShiper = idShiper;
        this.vehicle = vehicle;
        this.discountCode = discountCode;
        this.confirmPickup = confirmPickup;
        this.confirmPayment = confirmPayment;
        this.confirmComplete = confirmComplete;
        this.createDay = createDay;
        this.phoneNumber = phoneNumber;
        this.note = note;
    }

    public Order() {
    }

    public double getLongitudeRecive() {
        return longitudeRecive;
    }

    public void setLongitudeRecive(double longitudeRecive) {
        this.longitudeRecive = longitudeRecive;
    }

    public double getLatitudeRecive() {
        return latitudeRecive;
    }

    public void setLatitudeRecive(double latitudeRecive) {
        this.latitudeRecive = latitudeRecive;
    }

    public double getLongitudePickup() {
        return longitudePickup;
    }

    public void setLongitudePickup(double longitudePickup) {
        this.longitudePickup = longitudePickup;
    }

    public double getLatitudePickup() {
        return latitudePickup;
    }

    public void setLatitudePickup(double latitudePickup) {
        this.latitudePickup = latitudePickup;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIdShiper() {
        return idShiper;
    }

    public void setIdShiper(String idShiper) {
        this.idShiper = idShiper;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public boolean isConfirmPickup() {
        return confirmPickup;
    }

    public void setConfirmPickup(boolean confirmPickup) {
        this.confirmPickup = confirmPickup;
    }

    public boolean isConfirmPayment() {
        return confirmPayment;
    }

    public void setConfirmPayment(boolean confirmPayment) {
        this.confirmPayment = confirmPayment;
    }

    public boolean isConfirmComplete() {
        return confirmComplete;
    }

    public void setConfirmComplete(boolean confirmComplete) {
        this.confirmComplete = confirmComplete;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
