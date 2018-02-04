package com.psquare.databinding;

import android.app.Application;

import com.psquare.databinding.utils.network.IService;
import com.psquare.databinding.utils.network.ServiceFactory;

import timber.log.Timber;

/**
 * Created by Paresh on 03-02-2018
 */

public class App extends Application {

    private static IService service;

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        service = ServiceFactory.createRetrofitService(IService.class, IService.SERVICE_ENDPOINT);
    }

    public IService getService() {
        return service;
    }
}
