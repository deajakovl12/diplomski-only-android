package com.androiddiplomski.data.service;


import com.androiddiplomski.data.api.models.request.LoginRequest;
import com.androiddiplomski.data.api.models.response.LoginApiResponse;
import com.androiddiplomski.data.api.models.response.MovieApiResponse;

import io.reactivex.Single;

public final class NetworkServiceImpl implements NetworkService {

    private final TemplateAPI templateAPI;

    public NetworkServiceImpl(final TemplateAPI templateAPI) {
        this.templateAPI = templateAPI;
    }


    @Override
    public Single<MovieApiResponse> movieInfo() {
        return Single.defer(() -> templateAPI.movieInfo());
    }

    @Override
    public Single<LoginApiResponse> loginUser(LoginRequest loginRequest) {
        return Single.defer(() -> templateAPI.loginUser(loginRequest));
    }
}
