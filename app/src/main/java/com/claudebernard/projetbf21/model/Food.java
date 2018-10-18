package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food {

    @SerializedName("idFood")
    @Expose
    private Integer _id;

    @SerializedName("name")
    @Expose
    private String _name;

    @SerializedName("brand")
    @Expose
    private String _brand;

    @SerializedName("portionSize")
    @Expose
    private String _portionSize;

    @SerializedName("nutrients")
    @Expose
    private List<FoodNutrients> _foodNutrients;

    @SerializedName("macros")
    @Expose
    private List<FoodMacros> _foodMacros;

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

    public List<FoodNutrients> get_foodNutrients() {
        return _foodNutrients;
    }

    public void set_foodNutrients(List<FoodNutrients> _foodNutrients) {
        this._foodNutrients = _foodNutrients;
    }

    public List<FoodMacros> get_foodMacros() {
        return _foodMacros;
    }

    public void set_foodMacros(List<FoodMacros> _foodMacros) {
        this._foodMacros = _foodMacros;
    }
}
