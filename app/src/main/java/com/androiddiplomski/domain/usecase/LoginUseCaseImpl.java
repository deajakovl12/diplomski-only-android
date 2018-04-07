package com.androiddiplomski.domain.usecase;


import com.androiddiplomski.data.api.models.request.LoginRequest;
import com.androiddiplomski.data.api.models.response.LoginApiResponse;
import com.androiddiplomski.data.service.NetworkService;

import io.reactivex.Single;

public class LoginUseCaseImpl implements LoginUseCase {

    private final NetworkService networkService;

    public LoginUseCaseImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Single<LoginApiResponse> loginUser(LoginRequest loginRequest) {
        return Single.defer(()-> networkService.loginUser(loginRequest));
    }
}
