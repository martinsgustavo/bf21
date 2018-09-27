package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Food {

    @SerializedName("")
    @Expose
    private int _id;

    @SerializedName("")
    @Expose
    private String _name;

    @SerializedName("")
    @Expose
    private String _brand;

    @SerializedName("")
    @Expose
    private String _portionSize;


    //=====


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_brand() {
        return _brand;
    }

    public void set_brand(String _brand) {
        this._brand = _brand;
    }

    public String get_portionSize() {
        return _portionSize;
    }

    public void set_portionSize(String _portionSize) {
        this._portionSize = _portionSize;
    }
}
