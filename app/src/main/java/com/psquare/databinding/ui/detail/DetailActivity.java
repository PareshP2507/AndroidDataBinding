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
        // Set Navigation Click listener to toolbar
        mBinding.setNavClickListener(v -> onBackPressed());

        // Initializing presenter
        presenter = new DetailPresenter(this, service);
        presenter.attachView(this);
        mBinding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        userDetailList = new ArrayList<>();
        detailAdapter = new UserDetailAdapter(userDetailList);
        mBinding.recyclerView.setAdapter(detailAdapter);

        // Let's command presenter to process intent extras
        presenter.getIntentExtras();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView(); // detachView from presenter
        presenter.clear();      // Clearing presenter stuffs
        mBinding.unbind();      // unbind
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onUserRetrieval(User user) {
        mBinding.setUser(user); // Setting variable to binding
        mBinding.executePendingBindings(); // Forcing bindings

        // Filling up user info to recyclerView
        userDetailList.add(user.getGistsUrl());
        userDetailList.add(user.getHtmlUrl());
        userDetailList.add(user.getOrganizationsUrl());
        userDetailList.add(user.getReceivedEventsUrl());
        userDetailList.add(user.getReposUrl());
        userDetailList.add(user.getStarredUrl());
        userDetailList.add(user.getUrl());

        detailAdapter.notifyDataSetChanged();

        /*
        * Command presenter to get count of following users and followers
        * I'm getting all users from urls given by github allUsers service
        * count will be, number of  records in the response
        */
        presenter.getFollowingCount(user);
    }

    @Override
    public void onFollowerCountRetrieval(List<Integer> count) {
        // Setting Followers and following count
        mBinding.includedHeader.tvFollowers.setText(String.valueOf(count.get(0)));
        mBinding.includedHeader.tvFollowing.setText(String.valueOf(count.get(1)));
    }

    @Override
    public void onError(String message) {
        Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
