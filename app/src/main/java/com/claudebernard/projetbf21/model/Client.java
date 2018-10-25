package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("idClient")
    @Expose
    private Integer _id;

    @SerializedName("name")
    @Expose
    private String _name;

    @SerializedName("age")
    @Expose
    private Integer _age;

    @SerializedName("gender")
    @Expose
    private Character _gender;

    @SerializedName("email")
    @Expose
    private String _eMail;

    @SerializedName("phoneNumber")
    @Expose
    private String _phoneNumber;

    @SerializedName("height")
    @Expose
    private Double _height;

    @SerializedName("weight")
    @Expose
    private Double _weight;

    @SerializedName("bodyFatPercentage")
    @Expose
    private Integer _bodyFatPercentage;

    @SerializedName("bmr")
    @Expose
    private Double _bmr;

    @SerializedName("tdce")
    @Expose
    private Double _tdce;

    @SerializedName("clientGoal")
    @Expose
    private ClientGoal clientGoal;

    @SerializedName("activityLevel")
    @Expose
    private ClientActivityLevel clientactivityLevel;

    @SerializedName("proteinRequirement")
    @Expose
    private ClientProteinRequirement clientProteinRequirement;


    //=====
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Integer get_age() {
        return _age;
    }

    public void set_age(Integer _age) {
        this._age = _age;
    }

    public Character get_gender() {
        return _gender;
    }

    public void set_gender(Character _gender) {
        this._gender = _gender;
    }

    public String get_eMail() {
        return _eMail;
    }

    public void set_eMail(String _eMail) {
        this._eMail = _eMail;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public Double get_height() {
        return _height;
    }

    public void set_height(Double _height) {
        this._height = _height;
    }

    public Double get_weight() {
        return _weight;
    }

    public void set_weight(Double _weight) {
        this._weight = _weight;
    }

    public Integer get_bodyFatPercentage() {
        return _bodyFatPercentage;
    }

    public void set_bodyFatPercentage(Integer _bodyFatPercentage) {
        this._bodyFatPercentage = _bodyFatPercentage;
    }

    public Double get_bmr() {
        return _bmr;
    }

    public void set_bmr(Double _bmr) {
        this._bmr = _bmr;
    }

    public Double get_tdce() {
        return _tdce;
    }

    public void set_tdce(Double _tdce) {
        this._tdce = _tdce;
    }

    public ClientGoal getClientGoal() {
        return clientGoal;
    }

    public void setClientGoal(ClientGoal clientGoal) {
        this.clientGoal = clientGoal;
    }

    public ClientActivityLevel getClientactivityLevel() {
        return clientactivityLevel;
    }

    public void setClientactivityLevel(ClientActivityLevel clientactivityLevel) {
        this.clientactivityLevel = clientactivityLevel;
    }

    public ClientProteinRequirement getClientProteinRequirement() {
        return clientProteinRequirement;
    }

    public void setClientProteinRequirement(ClientProteinRequirement clientProteinRequirement) {
        this.clientProteinRequirement = clientProteinRequirement;
    }
}
