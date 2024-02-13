package com.example.ratiopack.model;

import java.io.Serializable;

public class SolidProfile implements Serializable {
    String poNumber;
    String cartonCount;
    String cartonNumber;
    String upcNumber;
    String maxPieceCount;
    String barCode;

    public SolidProfile() {
    }

    public SolidProfile(String poNumber, String cartonCount, String cartonNumber, String upcNumber, String maxPieceCount, String barCode) {
        this.poNumber = poNumber;
        this.cartonCount = cartonCount;
        this.cartonNumber = cartonNumber;
        this.upcNumber = upcNumber;
        this.maxPieceCount = maxPieceCount;
        this.barCode = barCode;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getCartonCount() {
        return cartonCount;
    }

    public void setCartonCount(String cartonCount) {
        this.cartonCount = cartonCount;
    }

    public String getCartonNumber() {
        return cartonNumber;
    }

    public void setCartonNumber(String cartonNumber) {
        this.cartonNumber = cartonNumber;
    }

    public String getUpcNumber() {
        return upcNumber;
    }

    public void setUpcNumber(String upcNumber) {
        this.upcNumber = upcNumber;
    }

    public String getMaxPieceCount() {
        return maxPieceCount;
    }

    public void setMaxPieceCount(String maxPieceCount) {
        this.maxPieceCount = maxPieceCount;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return "SolidProfile{" +
                "poNumber='" + poNumber + '\'' +
                ", cartonCount='" + cartonCount + '\'' +
                ", cartonNumber='" + cartonNumber + '\'' +
                ", upcNumber='" + upcNumber + '\'' +
                ", maxPieceCount='" + maxPieceCount + '\'' +
                ", barCode='" + barCode + '\'' +
                '}';
    }
}
