package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodPlanBD {

    @SerializedName("idFoodPlan")
    @Expose
    private Integer _idFoodPlan;

    @SerializedName("planName")
    @Expose
    private String _namePlan;

    @SerializedName("client")
    @Expose
    private Client _client;

    @SerializedName("coach")
    @Expose
    private Coach _coach;

    @SerializedName("planDays")
    @Expose
    private List<PlanDaysBD> _planDays;


    //=====


    public Integer get_idFoodPlan() {
        return _idFoodPlan;
    }

    public void set_idFoodPlan(Integer _idFoodPlan) {
        this._idFoodPlan = _idFoodPlan;
    }

    public String get_namePlan() {
        return _namePlan;
    }

    public void set_namePlan(String _namePlan) {
        this._namePlan = _namePlan;
    }

    public Client get_client() {
        return _client;
    }

    public void set_client(Client _client) {
        this._client = _client;
    }

    public Coach get_coach() {
        return _coach;
    }

    public void set_coach(Coach _coach) {
        this._coach = _coach;
    }

    public List<PlanDaysBD> get_planDays() {
        return _planDays;
    }

    public void set_planDays(List<PlanDaysBD> _planDays) {
        this._planDays = _planDays;
    }
}
