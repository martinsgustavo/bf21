package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodMacros {

    @SerializedName("idMacro")
    @Expose
    private Integer _idMacro;

    @SerializedName("macro")
    @Expose
    private String _macro;

    //------

    public Integer get_idMacro() {
        return _idMacro;
    }

    public void set_idMacro(Integer _idMacro) {
        this._idMacro = _idMacro;
    }

    public String get_macro() {
        return _macro;
    }

    public void set_macro(String _macro) {
        this._macro = _macro;
    }
}
