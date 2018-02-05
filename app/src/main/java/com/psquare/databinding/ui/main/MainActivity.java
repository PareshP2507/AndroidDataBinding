package com.psquare.databinding.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.ActivityMainBinding;
import com.psquare.databinding.ui.BaseActivity;
import com.psquare.databinding.ui.interf.OnLoadMoreListener;
import com.psquare.databinding.ui.main.adapter.UserAdapter;
import com.psquare.databinding.ui.main.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View, OnLoadMoreListener {

    private ActivityMainBinding mBinding;
    private MainPresenter presenter;
    private UserAdapter adapter;
    private List<User> mDataSet = new ArrayList<>();
    private int since = 0;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void iniImpl() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        adapter = new UserAdapter(mBinding.recyclerView, mDataSet, this) {
            @Override
            protected void onItemClick(User user) {
                presenter.handleRowClick(user);
            }
        };
        mBinding.recyclerView.setAdapter(adapter);
        presenter = new MainPresenter(this, service);
        presenter.attachView(this);

        loadUsers();
    }

    private void loadUsers() {
        presenter.getAllUsers(String.valueOf(since));
    }

    @Override
    public void showLoader() {
        mBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void onUserRetrieval(List<User> userList) {
        if (since != 0) {
            mDataSet.remove(mDataSet.size() - 1);
            adapter.notifyItemRemoved(mDataSet.size());
        }
        new Thread(() -> {
            for (User user : userList) {
                mDataSet.add(user);
                mHandler.post(() -> adapter.notifyItemInserted(mDataSet.size() - 1));
            }
        }).start();

        since = userList.get(userList.size() - 1).getId();
        adapter.setLoaded();
    }

    @Override
    public void onError(String message) {
        Snackbar.make(mBinding.recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        adapter.clear();
        presenter.clear();
        mHandler.removeCallbacksAndMessages(null);
        mBinding.unbind();
        super.onDestroy();
    }

    @Override
    public void onLoadMore() {
        mDataSet.add(null);
        mHandler.post(() -> adapter.notifyItemInserted(mDataSet.size() - 1));
        loadUsers();
    }
}
