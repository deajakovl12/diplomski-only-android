package com.androiddiplomski.domain.usecase;


import com.androiddiplomski.data.api.models.request.LoginRequest;
import com.androiddiplomski.data.api.models.response.LoginApiResponse;

import io.reactivex.Single;

public interface LoginUseCase {

    Single<LoginApiResponse> loginUser(LoginRequest loginRequest);

}
