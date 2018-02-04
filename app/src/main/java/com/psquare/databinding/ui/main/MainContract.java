package com.psquare.databinding.ui.main;

import com.psquare.databinding.ui.main.model.User;

import java.util.List;

/**
 * Created by Paresh on 04-02-2018
 */

public class MainContract {

    interface View {

        void showLoader();

        void hideLoader();

        void onUserRetrieval(List<User> userList);

        void onError(String message);
    }

    interface Presenter {

        void getAllUsers(String since);

        void clear();
    }
}
