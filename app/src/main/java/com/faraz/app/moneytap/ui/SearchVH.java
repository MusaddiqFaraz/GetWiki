package com.faraz.app.moneytap.ui;

import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.faraz.app.moneytap.R;
import com.faraz.app.moneytap.data_manager.api.Page;

/**
 * Created by root on 3/9/18.
 */

public class SearchVH extends RecyclerView.ViewHolder {

    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvDescription;

    public SearchVH(View itemView) {
        super(itemView);
        ivImage = itemView.findViewById(R.id.ivImage);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
    }


    void bindSearchItem(Page page, int position) {
        if (page.getThumbnail() != null)
            Glide.with(itemView.getContext())
                    .load(page.getThumbnail().getSource())
                    .into(ivImage);


        tvTitle.setText(page.getTitle());
        if (page.getTerms() != null && page.getTerms().getDescription() != null && !page.getTerms().getDescription().isEmpty())
            tvDescription.setText(page.getTerms().getDescription().get(0));

        itemView.setOnClickListener(view -> {

            String url = "http://en.wikipedia.org/?curid=" + page.getPageid();

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(Color.WHITE);
            CustomTabsIntent customTabsIntent = builder.build();

            customTabsIntent.launchUrl(itemView.getContext(), Uri.parse(url));

        });
    }


}
