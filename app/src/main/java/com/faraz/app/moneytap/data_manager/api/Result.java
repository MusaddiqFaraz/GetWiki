package com.faraz.app.moneytap.data_manager.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 2/9/18.
 */

public class Result {
    @SerializedName("batchcomplete")
    @Expose
    private Boolean batchcomplete;
    @SerializedName("query")
    @Expose
    private Query query;
    private final static long serialVersionUID = 4932314003210915519L;

    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }



    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
