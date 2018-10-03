package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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

    @SerializedName("")
    @Expose
    private ArrayList<FoodNutrient> _listNutrients;

    @SerializedName("")
    @Expose
    private ArrayList<FoodMacro> _listMacro;


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

    public ArrayList<FoodNutrient> get_listNutrients() {
        return _listNutrients;
    }

    public void set_listNutrients(ArrayList<FoodNutrient> _listNutrients) {
        this._listNutrients = _listNutrients;
    }

    public ArrayList<FoodMacro> get_listMacro() {
        return _listMacro;
    }

    public void set_listMacro(ArrayList<FoodMacro> _listMacro) {
        this._listMacro = _listMacro;
    }
}