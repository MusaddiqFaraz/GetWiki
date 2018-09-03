package com.faraz.app.moneytap.data_manager.api;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root on 2/9/18.
 */

@Entity
public class Page implements Serializable
{

    @PrimaryKey @SerializedName("pageid")
    @Expose
    private Integer pageid;
    @SerializedName("ns")
    @Expose
    private Integer ns;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("index")
    @Expose
    private Integer index;
     @SerializedName("thumbnail")
    @Expose
    @Embedded private Thumbnail thumbnail;
    @SerializedName("terms")
    @Expose
    @Embedded private Terms terms;


    private Long timeStamp;

    private final static long serialVersionUID = -6417071952245183937L;

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageid=" + pageid +
                ", ns=" + ns +
                ", title='" + title + '\'' +
                ", index=" + index +
                ", thumbnail=" + thumbnail +
                ", terms=" + terms +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
