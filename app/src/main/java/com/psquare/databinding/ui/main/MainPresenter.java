package com.psquare.databinding.ui.main;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.psquare.databinding.R;
import com.psquare.databinding.ui.BasePresenter;
import com.psquare.databinding.ui.SimpleObserver;
import com.psquare.databinding.ui.detail.DetailActivity;
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

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final MainContract.View mView;
    private final IService service;
    private CompositeDisposable disposable;

    public MainPresenter(MainContract.View mView, IService service) {
        super(mView);
        this.mView = mView;
        this.service = service;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void getAllUsers(String since) {
        if (since.equals("0")) mView.showLoader();
        service.getAllUsers(since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        if (isViewAttached()) {
                            if (since.equals("0"))mView.hideLoader();
                            mView.onUserRetrieval(users);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error on user retrieval: %s", e.getMessage());
                        if (isViewAttached()) {
                            if (since.equals("0"))mView.hideLoader();
                            mView.onError(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void clear() {
        super.detachView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void handleRowClick(User user, View view) {
        if (isViewAttached()) {
            Intent intent = new Intent(mView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.ARG_USER, user);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    ((MainActivity) mView.getContext()), view, "pic_trans"
            );
            mView.getContext().startActivity(intent, options.toBundle());
        }
    }
}
