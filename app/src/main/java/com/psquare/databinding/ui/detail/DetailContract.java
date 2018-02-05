package com.psquare.databinding.ui.detail;

import android.content.Context;

import com.psquare.databinding.ui.main.model.User;

/**
 * Created by paresh on 05-02-2018
 */

public class DetailContract {

    interface View {

        Context getContext();

        void onUserRetrieval(User user);

        void onError(String message);
    }

    interface Presenter {

        void getIntentExtras();

        void getFollowingCount(User user);

        void clear();
    }
}
