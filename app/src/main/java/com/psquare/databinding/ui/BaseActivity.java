package com.psquare.databinding.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.psquare.databinding.App;
import com.psquare.databinding.utils.network.IService;

/**
 * Created by Paresh on 04-02-2018
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected IService service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivity();
        iniImpl();
    }

    private void initActivity() {
        service = ((App) getApplication()).getService();
    }

    protected abstract void iniImpl();
}
