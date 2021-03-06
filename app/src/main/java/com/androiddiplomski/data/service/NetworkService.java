package com.androiddiplomski.data.service;


import com.androiddiplomski.data.api.models.request.LoginRequest;
import com.androiddiplomski.data.api.models.response.LoginApiResponse;
import com.androiddiplomski.data.api.models.response.MovieApiResponse;

import io.reactivex.Single;

public interface NetworkService {

    Single<MovieApiResponse> movieInfo();

    Single<LoginApiResponse> loginUser(LoginRequest loginRequest);
}
