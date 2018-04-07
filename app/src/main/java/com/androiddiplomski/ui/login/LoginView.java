package com.androiddiplomski.ui.login;


import com.androiddiplomski.data.api.models.response.LoginApiResponse;

public interface LoginView {

    void loginSuccessfull(LoginApiResponse loginApiResponse);
    void loginFailure();

}
