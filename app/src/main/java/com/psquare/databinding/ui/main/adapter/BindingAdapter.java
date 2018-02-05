package com.psquare.databinding.ui.main.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Paresh on 04-02-2018
 */

public class BindingAdapter {

    @android.databinding.BindingAdapter("imageUrl")
    public static void loadUserImage(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }
}
