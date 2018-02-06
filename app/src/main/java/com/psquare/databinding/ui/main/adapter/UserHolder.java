package com.psquare.databinding.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.psquare.databinding.databinding.RowUserBinding;
import com.psquare.databinding.ui.main.model.User;

/**
 * Created by Paresh on 04-02-2018
 */

abstract class UserHolder extends RecyclerView.ViewHolder {

    private RowUserBinding binding;

    UserHolder(RowUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.getRoot().setOnClickListener(v -> onRowClick(getAdapterPosition(), binding.ivPic));
    }

    void bind(User user) {
        binding.setUser(user);
        binding.executePendingBindings();
    }

    abstract void onRowClick(int position, View view);
}
