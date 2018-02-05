package com.psquare.databinding.ui.detail.adapter;

import android.support.v7.widget.RecyclerView;

import com.psquare.databinding.databinding.RowUserDetailBinding;

/**
 * Created by Paresh on 05-02-2018
 */

class UserDetailHolder extends RecyclerView.ViewHolder {

    private RowUserDetailBinding binding;

    UserDetailHolder(RowUserDetailBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(String detail) {
        this.binding.setString(detail);
        this.binding.executePendingBindings();
    }
}
