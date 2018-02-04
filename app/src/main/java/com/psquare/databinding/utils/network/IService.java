package com.psquare.databinding.utils.network;

import com.psquare.databinding.ui.main.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PARESH ANDROID on 4/7/2017
 */

public interface IService {

    String SERVICE_ENDPOINT = "https://api.github.com/";

    @GET("users")
    Observable<List<User>> getAllUsers(@Query("since") String lastUserId);
}
