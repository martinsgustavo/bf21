package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodPlan {

    @SerializedName("idFoodPlan")
    @Expose
    private int _idFoodPlan;

    @SerializedName("namePlan")
    @Expose
    private String _namePlan;

    @SerializedName("idCoach")
    @Expose
    private int _idCoach;

    @SerializedName("idClient")
    @Expose
    private int _idClient;


    //=====
    public int get_idFoodPlan() {
        return _idFoodPlan;
    }

    public void set_idFoodPlan(int _idFoodPlan) {
        this._idFoodPlan = _idFoodPlan;
    }

    public String get_namePlan() {
        return _namePlan;
    }

    public void set_namePlan(String _namePlan) {
        this._namePlan = _namePlan;
    }

    public int get_idCoach() {
        return _idCoach;
    }

    public void set_idCoach(int _idCoach) {
        this._idCoach = _idCoach;
    }

    public int get_idClient() {
        return _idClient;
    }

    public void set_idClient(int _idClient) {
        this._idClient = _idClient;
    }
}