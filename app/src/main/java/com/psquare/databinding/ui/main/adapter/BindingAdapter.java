package com.psquare.databinding.ui.main.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Paresh on 04-02-2018
 */

public class BindingAdapter {

    @android.databinding.BindingAdapter({"bind:imageUrl", "bind:placeholder" , "bind:error"})
    public static void loadUserImage(ImageView imageView, String url, Drawable placeHolder, Drawable error) {
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.circleCropTransform().placeholder(placeHolder).error(error))
                .into(imageView);
    }
}
