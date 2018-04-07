package com.androiddiplomski.ui.login;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.androiddiplomski.R;
import com.androiddiplomski.data.api.models.request.LoginRequest;
import com.androiddiplomski.data.api.models.response.LoginApiResponse;
import com.androiddiplomski.injection.component.ActivityComponent;
import com.androiddiplomski.ui.base.activities.BaseActivity;
import com.androiddiplomski.ui.home.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView{


    @Inject
    LoginPresenter presenter;

    @BindView(R.id.login_activity_input_username)
    TextInputEditText txtUsername;

    @BindView(R.id.login_activity_input_password)
    TextInputEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login)
    public void loginUser(){
        if(!txtUsername.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {
            presenter.loginUser(new LoginRequest(txtUsername.getText().toString(), txtPassword.getText().toString()));
        }
        else{
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dispose();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void loginSuccessfull(LoginApiResponse loginApiResponse) {
        startActivity(HomeActivity.createIntent(this, loginApiResponse));
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, "Login failure", Toast.LENGTH_SHORT).show();
    }
}
