package com.example.ratiopack.model;

import java.io.Serializable;

public class Cons implements Serializable {
    String buyer,method,poNumber,cartonCount,pcNumber;

    public Cons() {
    }

    public Cons(String buyer, String method, String poNumber, String cartonCount, String pcNumber) {
        this.buyer = buyer;
        this.method = method;
        this.poNumber = poNumber;
        this.cartonCount = cartonCount;
        this.pcNumber = pcNumber;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(String pcNumber) {
        this.pcNumber = pcNumber;
    }
}
