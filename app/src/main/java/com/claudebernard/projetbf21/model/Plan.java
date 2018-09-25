package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plan {

    @SerializedName("CLIENT")
    @Expose
    String _client;

    @SerializedName("NAME")
    @Expose
    String _name;

    @SerializedName("OBJECTIVE")
    @Expose
    String _objective;

    @SerializedName("PROTEIN")
    @Expose
    String _protein;

    @SerializedName("LIPIDS")
    @Expose
    String _lipids;

    @SerializedName("GLYCIDES")
    @Expose
    String _glycides;

    @SerializedName("PERIOD_START")
    @Expose
    String _periodStart;

    @SerializedName("PERIOD_END")
    @Expose
    String _periodEnd;

    public String get_client() {
        return _client;
    }

    public void set_client(String _client) {
        this._client = _client;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_objective() {
        return _objective;
    }

    public void set_objective(String _objectif) {
        this._objective = _objectif;
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

    public String get_periodStart() {
        return _periodStart;
    }

    public void set_periodStart(String _periodStart) {
        this._periodStart = _periodStart;
    }

    public String get_periodEnd() {
        return _periodEnd;
    }

    public void set_periodEnd(String _periodEnd) {
        this._periodEnd = _periodEnd;
    }
}
