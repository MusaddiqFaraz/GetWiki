package com.faraz.app.moneytap.data_manager.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root on 2/9/18.
 */

public class Redirect implements Serializable
{

    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    private final static long serialVersionUID = 2873191367832239636L;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
