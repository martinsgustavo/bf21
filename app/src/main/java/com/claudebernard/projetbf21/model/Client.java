package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

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

    @SerializedName("clientGoal")
    @Expose
    private ClientGoal clientGoal;

    @SerializedName("tdee")
    @Expose
    private String _tdee;


    //=====

    public int get_id() { return _id; }

    public void set_id(int _id) { this._id = _id; }

    public String get_name() { return _name; }

    public void set_name(String _name) { this._name = _name; }

    public int get_age() { return _age; }

    public void set_age(int _age) { this._age = _age; }

    public String get_eMail() { return _eMail; }

    public void set_eMail(String _eMail) { this._eMail = _eMail; }

    public String get_phoneNumber() { return _phoneNumber; }

    public void set_phoneNumber(String _phoneNumber) { this._phoneNumber = _phoneNumber; }

    public double get_height() { return _height; }

    public void set_height(double _height) { this._height = _height; }

    public double get_weight() { return _weight; }

    public void set_weight(double _weight) { this._weight = _weight; }

    public int get_bodyFatPercentage() { return _bodyFatPercentage; }

    public void set_bodyFatPercentage(int _bodyFatPercentage) { this._bodyFatPercentage = _bodyFatPercentage; }

    public ClientGoal getClientGoal() { return clientGoal; }

    public void setClientGoal(ClientGoal clientGoal) { this.clientGoal = clientGoal; }

    public String get_tdee() { return _tdee; }

    public void set_tdee(String _tdee) { this._tdee = _tdee; }
}
