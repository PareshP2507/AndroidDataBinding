package com.psquare.databinding.ui;

/**
 * class is responsible for handling LifeCycleEvents to prevent unnecessary crashes.
 * For example, Suppose you have to process network result on mainThread and Activity
 * has been destroyed?
 * @author Paresh P.
 */

public class BasePresenter<T> {

    private T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
    }

    /**
     * @param view -> View to be attached
     */
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * Detach View
     */
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * Status of View
     * @return -> true if View is attached, false if not
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}
