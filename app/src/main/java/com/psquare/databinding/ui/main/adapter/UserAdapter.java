package com.psquare.databinding.ui.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.RowProgressBinding;
import com.psquare.databinding.databinding.RowUserBinding;
import com.psquare.databinding.ui.BaseRecyclerAdapter;
import com.psquare.databinding.ui.interf.OnLoadMoreListener;
import com.psquare.databinding.ui.main.model.User;

import java.util.List;

/**
 * Created by Paresh on 04-02-2018
 */

public abstract class UserAdapter extends BaseRecyclerAdapter {

    private List<User> mData;

    public UserAdapter(RecyclerView recyclerView,
                       List<User> mData, OnLoadMoreListener onLoadMoreListener) {
        this.mData = mData;
        super.attachRecyclerView(recyclerView, onLoadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_LOADER:
                RowProgressBinding binding1 = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()), R.layout.row_progress, parent, false
                );
                return new ProgressHolder(binding1);
            case TYPE_ITEM:
                RowUserBinding binding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()), R.layout.row_user, parent, false
                );
                return new UserHolder(binding) {
                    @Override
                    void onRowClick(int position, View v) {
                        onItemClick(mData.get(position), v);
                    }
                };
            default:
                return null;
        }
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

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) == null ? TYPE_LOADER : TYPE_ITEM;
    }

    public void clear() {
        super.clear();
    }

    public void setLoaded() {
        super.setLoaded();
    }

    protected abstract void onItemClick(User user, View view);
}
