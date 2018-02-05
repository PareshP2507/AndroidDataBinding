package com.psquare.databinding.ui.detail;

import android.content.Intent;
import android.os.Bundle;

import com.psquare.databinding.R;
import com.psquare.databinding.ui.BasePresenter;
import com.psquare.databinding.ui.main.model.User;
import com.psquare.databinding.utils.network.IService;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by paresh on 05-02-2018
 */

public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {

    private final DetailContract.View mView;
    private final IService service;
    private CompositeDisposable compositeDisposable;

    DetailPresenter(DetailContract.View mView, IService service) {
        super(mView);
        this.mView = mView;
        this.service = service;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getIntentExtras() {
        Intent i = ((DetailActivity) mView.getContext()).getIntent();
        Bundle b = i.getExtras();
        if (null != b && b.containsKey(DetailActivity.ARG_USER)) {
            mView.onUserRetrieval(b.getParcelable(DetailActivity.ARG_USER));
        } else {
            mView.onError(mView.getContext().getString(R.string.invalid_user));
        }
    }

    @Override
    public void getFollowingCount(User user) {

    }

    @Override
    public void clear() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
