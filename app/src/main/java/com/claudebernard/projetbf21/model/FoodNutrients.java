package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodNutrients {

    @SerializedName("idNutrient")
    @Expose
    private Integer _id;

    @SerializedName("nutrient")
    @Expose
    private String _nutrient;

    @SerializedName("isMacro")
    @Expose
    private Boolean _isMacro;

    public Integer get_id() { return _id; }

    public void set_id(Integer _id) { this._id = _id; }

    public String get_nutrient() { return _nutrient; }

    public void set_nutrient(String _nutrient) { this._nutrient = _nutrient; }

    public Boolean get_isMacro() { return _isMacro; }

    public void set_isMacro(Boolean _isMacro) { this._isMacro = _isMacro; }

}