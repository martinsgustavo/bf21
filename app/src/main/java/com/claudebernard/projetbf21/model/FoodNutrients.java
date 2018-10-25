package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodNutrients {

    @SerializedName("idNutrient")
    @Expose
    private Integer _id;

    @SerializedName("name")
    @Expose
    private String _name;

    @SerializedName("isMacro")
    @Expose
    private Boolean _isMacro;

    @SerializedName("total")
    @Expose
    private Double _total;


    //=====
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Boolean get_isMacro() {
        return _isMacro;
    }

    public void set_isMacro(Boolean _isMacro) {
        this._isMacro = _isMacro;
    }

    public Double get_total() {
        return _total;
    }

    public void set_total(Double _total) {
        this._total = _total;
    }
}
