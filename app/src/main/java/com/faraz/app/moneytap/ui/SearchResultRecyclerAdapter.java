package com.faraz.app.moneytap.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faraz.app.moneytap.R;
import com.faraz.app.moneytap.data_manager.api.Page;

import java.util.ArrayList;

/**
 * Created by root on 3/9/18.
 */

public class SearchResultRecyclerAdapter extends RecyclerView.Adapter<SearchVH> {

    private ArrayList<Page> pages;
    private Context context;

    public SearchResultRecyclerAdapter(ArrayList<Page> pages, Context context) {
        this.pages = pages;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_search_item,parent,false);

        return new SearchVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchVH holder, int position) {
        holder.bindSearchItem(pages.get(position),position);


    }

    @Override
    public int getItemCount() {
        return pages.size();
    }
}
