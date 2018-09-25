package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("name")
    @Expose
    String _name;

    @SerializedName("portion")
    @Expose
    String _portion;

    @SerializedName("calorie")
    @Expose
    String _calorie;

    @SerializedName("protein")
    @Expose
    String _protein;

    @SerializedName("lipids")
    @Expose
    String _lipids;

    @SerializedName("glycides")
    @Expose
    String _glycides;

    @SerializedName("fibre")
    @Expose
    String _fibre;

    @SerializedName("glycemicIndex")
    @Expose
    String _glycemicIndex;


    //=====
    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_portion() {
        return _portion;
    }

    public void set_portion(String _portion) {
        this._portion = _portion;
    }

    public String get_calorie() {
        return _calorie;
    }

    public void set_calorie(String _calorie) {
        this._calorie = _calorie;
    }

    public String get_protein() {
        return _protein;
    }

    public void set_protein(String _protein) {
        this._protein = _protein;
    }

    public String get_lipids() {
        return _lipids;
    }

    public void set_lipids(String _lipids) {
        this._lipids = _lipids;
    }

    public String get_glycides() {
        return _glycides;
    }

    public void set_glycides(String _glycides) {
        this._glycides = _glycides;
    }

    public String get_fibre() {
        return _fibre;
    }

    public void set_fibre(String _fibre) {
        this._fibre = _fibre;
    }

    public String get_glycemicIndex() {
        return _glycemicIndex;
    }

    public void set_glycemicIndex(String _glycemicIndex) {
        this._glycemicIndex = _glycemicIndex;
    }
}
