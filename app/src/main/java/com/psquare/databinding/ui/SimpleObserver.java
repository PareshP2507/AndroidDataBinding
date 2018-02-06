package com.psquare.databinding.ui;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by paresh on 06-02-2018
 */

public abstract class SimpleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
