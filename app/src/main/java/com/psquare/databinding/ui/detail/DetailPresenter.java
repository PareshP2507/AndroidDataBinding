package com.psquare.databinding.ui.detail;

import android.content.Intent;
import android.os.Bundle;

import com.psquare.databinding.R;
import com.psquare.databinding.ui.BasePresenter;
import com.psquare.databinding.ui.main.model.User;
import com.psquare.databinding.utils.network.IService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

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
        Observable<List<User>> follwerObs = service.getFollowingCount(user.getFollowersUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        String followingUrl = user.getFollowingUrl().replaceAll("", "");
        Observable<List<User>> follwingObs = service.getFollowingCount(followingUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable.zip(follwerObs, follwingObs, (users, users2) -> {
            List<Integer> sizeList = new ArrayList<>();
            sizeList.add(users.size());
            sizeList.add(users2.size());
            return sizeList;
        }).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                for (Integer i :
                        integers) {
                    Timber.d("## %s", i);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void clear() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
