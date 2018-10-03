package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodMacro {

    @SerializedName("")
    @Expose
    private int _id;

    @SerializedName("")
    @Expose
    private String _name;


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
}
