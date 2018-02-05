package com.psquare.databinding.ui.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.RowUserBinding;
import com.psquare.databinding.ui.main.model.User;

import java.util.List;

/**
 * Created by Paresh on 04-02-2018
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mData;

    public UserAdapter(List<User> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowUserBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.row_user, parent, false
        );
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserHolder) {
            ((UserHolder) holder).bind(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
