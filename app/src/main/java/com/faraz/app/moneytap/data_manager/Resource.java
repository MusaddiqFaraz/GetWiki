package com.faraz.app.moneytap.data_manager;

import com.faraz.app.moneytap.data_manager.api.Page;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public List<Page> getData() {
        return data;
    }

    public void setData(List<Page> data) {
        this.data = data;
    }

    enum Source {
        Database,
        Network
    }

    private Source source;
    private List<Page> data;

    public Resource(Source source, List<Page> data) {
        this.source = source;
        this.data = data;
    }
}
