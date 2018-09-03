package com.faraz.app.moneytap.data_manager.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root on 2/9/18.
 */


public class Thumbnail implements Serializable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    private final static long serialVersionUID = 4625702128143243379L;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "source='" + source + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
