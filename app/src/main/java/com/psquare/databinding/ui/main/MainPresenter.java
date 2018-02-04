package com.psquare.databinding.ui.main;

import com.psquare.databinding.ui.main.model.User;
import com.psquare.databinding.utils.network.IService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Paresh on 04-02-2018
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;
    private final IService service;
    private CompositeDisposable disposable;

    public MainPresenter(MainContract.View mView, IService service) {
        this.mView = mView;
        this.service = service;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void getAllUsers(String since) {
        mView.showLoader();
        service.getAllUsers(since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        mView.hideLoader();
                        mView.onUserRetrieval(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error on user retrieval: %s", e.getMessage());
                        mView.hideLoader();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void clear() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
