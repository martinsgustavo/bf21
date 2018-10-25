package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientGoal {

    @SerializedName("idClientGoal")
    @Expose
    private Integer _idClientGoal;

    @SerializedName("goal")
    @Expose
    private String _goal;


    //=====
    public Integer get_idClientGoal() {
        return _idClientGoal;
    }

    public void set_idClientGoal(Integer _idClientGoal) {
        this._idClientGoal = _idClientGoal;
    }

    public String get_goal() {
        return _goal;
    }

    public void set_goal(String _goal) {
        this._goal = _goal;
    }
}
