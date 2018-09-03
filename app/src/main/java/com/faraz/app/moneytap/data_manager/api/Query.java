package com.faraz.app.moneytap.data_manager.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 2/9/18.
 */

public class Query implements Serializable
{


    @SerializedName("pages")
    @Expose
    private List<Page> pages = null;
    private final static long serialVersionUID = 4217510675345874751L;


    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

}
