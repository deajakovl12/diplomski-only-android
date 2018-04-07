package com.androiddiplomski.ui.login;


import com.androiddiplomski.data.api.models.request.LoginRequest;

public interface LoginPresenter {

    void setView(LoginView view);

    void loginUser(LoginRequest loginRequest);

    void dispose();
}
