package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientGoal {

    @SerializedName("idClientGoal")
    @Expose
    private int idClientGoal;

    public int getIdClientGoal() { return idClientGoal; }

    public void setIdClientGoal(int idClientGoal) { this.idClientGoal = idClientGoal; }

}