package com.psquare.databinding.ui.detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.ActivityDetailBinding;
import com.psquare.databinding.ui.BaseActivity;
import com.psquare.databinding.ui.detail.adapter.UserDetailAdapter;
import com.psquare.databinding.ui.main.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paresh on 05-02-2018
 */

public class DetailActivity extends BaseActivity implements DetailContract.View {

    private ActivityDetailBinding mBinding;
    private DetailPresenter presenter;
    private List<String> userDetailList;
    private UserDetailAdapter detailAdapter;

    public static final String ARG_USER = "arg_user";

    @Override
    protected void iniImpl() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        presenter = new DetailPresenter(this, service);
        presenter.attachView(this);
        mBinding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        userDetailList = new ArrayList<>();
        detailAdapter = new UserDetailAdapter(userDetailList);
        mBinding.recyclerView.setAdapter(detailAdapter);
        presenter.getIntentExtras();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        presenter.clear();
        mBinding.unbind();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onUserRetrieval(User user) {
        mBinding.setUser(user);
        mBinding.executePendingBindings();

        userDetailList.add(user.getGistsUrl());
        userDetailList.add(user.getHtmlUrl());
        userDetailList.add(user.getOrganizationsUrl());
        userDetailList.add(user.getReceivedEventsUrl());
        userDetailList.add(user.getReposUrl());
        userDetailList.add(user.getStarredUrl());
        userDetailList.add(user.getUrl());

        detailAdapter.notifyDataSetChanged();

        presenter.getFollowingCount(user);
    }

    @Override
    public void onError(String message) {
        Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
