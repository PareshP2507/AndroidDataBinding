package com.psquare.databinding.ui;

/**
 * Created by paresh on 05-02-2018
 */

public class BasePresenter<T> {

    private T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
    }

    public void attachView(T view) {
        this.mView = view;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    public boolean isViewAttached() {
        return mView != null;
    }
}
