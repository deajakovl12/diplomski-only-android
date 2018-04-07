package com.androiddiplomski.injection.component;


import com.androiddiplomski.ui.home.HomeActivity;
import com.androiddiplomski.ui.login.LoginActivity;

public interface ActivityComponentActivityInjects {

    void inject(HomeActivity homeActivity);
    void inject(LoginActivity loginActivity);

}
