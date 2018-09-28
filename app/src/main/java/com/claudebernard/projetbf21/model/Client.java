package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Client {

    @SerializedName("idClient")
    @Expose
    private int _id;

    @SerializedName("name")
    @Expose
    private String _name;

    @SerializedName("age")
    @Expose
    private int _age;

    @SerializedName("email")
    @Expose
    private String _eMail;

    @SerializedName("phoneNumber")
    @Expose
    private String _phoneNumber;

    @SerializedName("height")
    @Expose
    private double _height;

    @SerializedName("weight")
    @Expose
    private double _weight;

    @SerializedName("bodyFatPercentage")
    @Expose
    private int _bodyFatPercentage;

    @SerializedName("clientGoal")
    @Expose
    private int _idClientGoal;

    @SerializedName("tdee")
    @Expose
    private String _tdee;

    @SerializedName("creationDate")
    @Expose
    private Date _dtCreation;

    @SerializedName("modificationDate")
    @Expose
    private Date _dtModification;


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

    public int get_idClientGoal() { return _idClientGoal; }

    public void set_idClientGoal(int _idClientGoal) { this._idClientGoal = _idClientGoal; }

    public String get_tdee() { return _tdee; }

    public void set_tdee(String _tdee) { this._tdee = _tdee; }

    public Date get_dtCreation() { return _dtCreation; }

    public void set_dtCreation(Date _dtCreation) { this._dtCreation = _dtCreation; }

    public Date get_dtModification() { return _dtModification; }

    public void set_dtModification(Date _dtModification) { this._dtModification = _dtModification; }
}