package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseServerArray {

    @SerializedName("httpStatus")
    @Expose
    private int httpStatus;

    @SerializedName("meta")
    @Expose
    private Object[] meta;

    @SerializedName("message")
    @Expose
    private String message;


    //=====
    public int getHttpStatus() { return httpStatus; }

    public void setHttpStatus(int httpStatus) { this.httpStatus = httpStatus; }

    public Object[] getMeta() { return meta; }

    public void setMeta(Object[] meta) { this.meta = meta; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
