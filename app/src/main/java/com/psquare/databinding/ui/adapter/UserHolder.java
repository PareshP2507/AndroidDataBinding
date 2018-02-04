package com.psquare.databinding.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.psquare.databinding.databinding.RowUserBinding;
import com.psquare.databinding.ui.main.model.User;

/**
 * Created by Paresh on 04-02-2018
 */

class UserHolder extends RecyclerView.ViewHolder {

    private RowUserBinding binding;

    UserHolder(RowUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(User user) {
        binding.setUser(user);
        binding.executePendingBindings();
    }
}
