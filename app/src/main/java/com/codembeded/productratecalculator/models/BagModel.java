package com.codembeded.productratecalculator.models;

public class BagModel {
    int bagId;
    String bagName;
    double bagPrice;

    public BagModel() {
    }

    public BagModel(int bagId, String bagName, double bagPrice) {
        this.bagId = bagId;
        this.bagName = bagName;
        this.bagPrice = bagPrice;
    }

    public int getBagId() {
        return bagId;
    }

    public void setBagId(int bagId) {
        this.bagId = bagId;
    }

    public String getBagName() {
        return bagName;
    }

    public void setBagName(String bagName) {
        this.bagName = bagName;
    }

    public double getBagPrice() {
        return bagPrice;
    }

    public void setBagPrice(double bagPrice) {
        this.bagPrice = bagPrice;
    }
}
