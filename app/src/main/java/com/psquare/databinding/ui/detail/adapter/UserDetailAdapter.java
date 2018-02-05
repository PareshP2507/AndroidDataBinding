package com.psquare.databinding.ui.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.RowUserDetailBinding;

import java.util.List;

/**
 * Created by Paresh on 05-02-2018
 */

public class UserDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mData;

    public UserDetailAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowUserDetailBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.row_user_detail, parent, false
        );
        return new UserDetailHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserDetailHolder) {
            ((UserDetailHolder) holder).bind(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
