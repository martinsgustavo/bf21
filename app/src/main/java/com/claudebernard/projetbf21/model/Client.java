package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("id")
    @Expose
    private String _id;

    @SerializedName("first_name")
    @Expose
    private String _firstName;

    @SerializedName("last_name")
    @Expose
    private String _lastName;

    @SerializedName("address")
    @Expose
    private String _address;

    @SerializedName("email")
    @Expose
    private String _eMail;

    @SerializedName("phone")
    @Expose
    private String _phone;

    @SerializedName("height")
    @Expose
    private String _height;

    @SerializedName("weight")
    @Expose
    private String _weight;

    @SerializedName("tdee")
    @Expose
    private String _tdee;


    //=====


    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_eMail() {
        return _eMail;
    }

    public void set_eMail(String _eMail) {
        this._eMail = _eMail;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_height() {
        return _height;
    }

    public void set_height(String _height) {
        this._height = _height;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }

    public String get_tdee() {
        return _tdee;
    }

    public void set_tdee(String _tdee) {
        this._tdee = _tdee;
    }
}
