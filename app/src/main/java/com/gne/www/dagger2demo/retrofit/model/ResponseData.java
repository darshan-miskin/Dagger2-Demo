package com.gne.www.dagger2demo.retrofit.model;

public class ResponseData {
    String id, product_name, supplier, unit_cost;
    int quantity;

    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public int getQuantity() {
        return quantity;
    }
}
