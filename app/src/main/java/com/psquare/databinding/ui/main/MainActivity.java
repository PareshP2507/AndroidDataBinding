package com.psquare.databinding.ui.main;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.psquare.databinding.R;
import com.psquare.databinding.databinding.ActivityMainBinding;
import com.psquare.databinding.ui.main.adapter.UserAdapter;
import com.psquare.databinding.ui.main.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View {

    private ActivityMainBinding mBinding;
    private MainPresenter presenter;
    private UserAdapter adapter;
    private List<User> mDataSet = new ArrayList<>();

    @Override
    protected void iniImpl() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        adapter = new UserAdapter(mDataSet);
        mBinding.recyclerView.setAdapter(adapter);
        presenter = new MainPresenter(this, service);

        presenter.getAllUsers(String.valueOf(0));
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
        mDataSet.addAll(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Snackbar.make(mBinding.recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.clear();
        mBinding.unbind();
        super.onDestroy();
    }
}
