package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Coach {

    @SerializedName("idCoach")
    @Expose
    private int _id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String _eMail;

    @SerializedName("phoneNumber")
    @Expose
    private String _phone;

    @SerializedName("login")
    @Expose
    private String _login;

    @SerializedName("password")
    @Expose
    private String _password;


    //=====


    public int get_id() { return _id; }

    public void set_id(int _id) { this._id = _id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String get_eMail() { return _eMail; }

    public void set_eMail(String _eMail) { this._eMail = _eMail; }

    public String get_phone() { return _phone; }

    public void set_phone(String _phone) { this._phone = _phone; }

    public String get_login() { return _login; }

    public void set_login(String _login) { this._login = _login; }

    public String get_password() { return _password; }

    public void set_password(String _password) { this._password = _password; }
}
