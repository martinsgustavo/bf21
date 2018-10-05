package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientProteinRequirement {

    @SerializedName("idProteinRequirement")
    @Expose
    private Integer _idProteinRequirement;

    @SerializedName("description")
    @Expose
    private String _description;

    @SerializedName("tax")
    @Expose
    private Double tax;

    public Integer get_idProteinRequirement() {
        return _idProteinRequirement;
    }

    public void set_idProteinRequirement(Integer _idProteinRequirement) {
        this._idProteinRequirement = _idProteinRequirement;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
