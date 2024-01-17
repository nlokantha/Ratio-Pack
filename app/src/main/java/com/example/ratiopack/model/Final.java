package com.example.ratiopack.model;

public class Final {
    String poNumber;
    int cartonNumber;
    int pcNumber;
    String color;
    String size;
    int quantity;
    long barcode;

    public Final() {
    }

    public Final(String poNumber, int cartonNumber, int pcNumber, String color, String size, int quantity, long barcode) {
        this.poNumber = poNumber;
        this.cartonNumber = cartonNumber;
        this.pcNumber = pcNumber;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    public Final(String color, String size, int quantity, long barcode) {
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return poNumber;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public int getCartonNumber() {
        return cartonNumber;
    }

    public void setCartonNumber(int cartonNumber) {
        this.cartonNumber = cartonNumber;
    }

    public int getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public int getBarcode() {
//        return barcode;
//    }
//
//    public void setBarcode(int barcode) {
//        this.barcode = barcode;
//    }


    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }
}
