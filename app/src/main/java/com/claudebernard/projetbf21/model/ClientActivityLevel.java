package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientActivityLevel {

    @SerializedName("idDailyActivityLevel")
    @Expose
    private Integer _idDailyActivityLevel;

    @SerializedName("description")
    @Expose
    private String _description;

    @SerializedName("tax")
    @Expose
    private Double tax;

    public Integer get_idDailyActivityLevel() {
        return _idDailyActivityLevel;
    }

    public void set_idDailyActivityLevel(Integer _idDailyActivityLevel) {
        this._idDailyActivityLevel = _idDailyActivityLevel;
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
