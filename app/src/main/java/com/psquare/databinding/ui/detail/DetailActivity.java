package com.psquare.databinding.ui.detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.ActivityDetailBinding;
import com.psquare.databinding.ui.BaseActivity;
import com.psquare.databinding.ui.main.model.User;

/**
 * Created by paresh on 05-02-2018
 */

public class DetailActivity extends BaseActivity implements DetailContract.View {

    private ActivityDetailBinding mBinding;
    private DetailPresenter presenter;

    public static final String ARG_USER = "arg_user";

    @Override
    protected void iniImpl() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        presenter = new DetailPresenter(this, service);
        presenter.getIntentExtras();
    }

    @Override
    protected void onDestroy() {
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
    }

    @Override
    public void onError(String message) {
        Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
