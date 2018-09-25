package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coach {

    @SerializedName("id")
    @Expose
    String _id;

    @SerializedName("first_name")
    @Expose
    String _firstName;

    @SerializedName("last_name")
    @Expose
    String _lastName;

    @SerializedName("address")
    @Expose
    String _address;

    @SerializedName("email")
    @Expose
    String _eMail;

    @SerializedName("phone")
    @Expose
    String _phone;

    @SerializedName("login")
    @Expose
    String _login;

    @SerializedName("password")
    @Expose
    String _password;


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

    public String get_login() {
        return _login;
    }

    public void set_login(String _login) {
        this._login = _login;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
