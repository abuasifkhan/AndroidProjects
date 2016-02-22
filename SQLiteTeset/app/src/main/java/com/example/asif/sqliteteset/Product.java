package com.example.asif.sqliteteset;

/**
 * Created by asif on 09/02/16.
 */
public class Product {
    private long _id;
    private String _productName;

    public Product(String _productName) {
        this._productName = _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public void set_id(long _id) {

        this._id = _id;
    }

    public String get_productName() {
        return _productName;
    }

    public long get_id() {

        return _id;
    }
}
