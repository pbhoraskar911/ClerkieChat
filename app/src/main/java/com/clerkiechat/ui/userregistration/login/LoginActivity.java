package com.clerkiechat.ui.userregistration.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clerkiechat.R;
import com.clerkiechat.di.ClerkieApplication;
import com.clerkiechat.di.component.ApplicationComponent;
import com.clerkiechat.ui.chatscreen.ChatScreenActivity;
import com.clerkiechat.ui.userregistration.signup.SignUpActivity;
import com.clerkiechat.utils.ViewUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Pranav Bhoraskar
 */

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {

    @BindView(R.id.userName)
    MaterialEditText userNameEditText;
    @BindView(R.id.password)
    MaterialEditText passwordEditText;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.button_signup)
    Button buttonSignup;
    @BindView(R.id.login_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.login_relative_layout)
    RelativeLayout loginRelativeLayout;
    @BindView(R.id.label_forgot_password)
    TextView forgotPasswordTextView;

    private String userName = "", password = "";

    private FirebaseAuth firebaseAuth;

    @Inject
    LoginPresenter loginPresenter;

    public static final String EMAIL_ADDRESS = "email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getApplicationComponent().injectLogin(this);
        initializeView();

        firebaseAuth = FirebaseAuth.getInstance();

        userNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    loginButton.setEnabled(true);
                }
                else {
                    loginButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0
                        && userNameEditText.getText().length() > 0) {
                    loginButton.setEnabled(true);
                }
                else {
                    loginButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initializeView() {
        loginPresenter.attachView(this);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ClerkieApplication) getApplication()).getApplicationComponent();
    }

    @OnClick({R.id.loginButton, R.id.button_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                if (ViewUtils.isInternetAvailable(this)) {
                    if (ViewUtils.checkPasswordValidity(passwordEditText)
                            && ViewUtils.checkEmailValidity(userNameEditText)) {
                        userName = userNameEditText.getText().toString();
                        password = passwordEditText.getText().toString();
                        loginPresenter.checkCredentialsValidity(firebaseAuth, userName, password);
                    }
                    else {
                        // check for phone
                        if (!ViewUtils.checkEmailValidity(userNameEditText)) {
                            userNameEditText.setError(getString(R.string.invalid_email));
                        }

                        if (!ViewUtils.checkPasswordValidity(passwordEditText)) {
                            passwordEditText.setError(getString(R.string.invalid_password_details));
                        }
                    }
                }
                else {
                    ViewUtils.createNoInternetDialog(this, R.string.network_error);
                }
                break;
            case R.id.button_signup:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openChatHome() {
        startActivity(new Intent(LoginActivity.this, ChatScreenActivity.class));
        finish();
    }

    @Override
    public void onCredentialsFailure() {
        displayLogInFailure(getString(R.string.login_failure));
    }

    private void displayLogInFailure(String title) {
        Snackbar.make(loginRelativeLayout, title, Snackbar.LENGTH_LONG)
                .show();
    }

    @OnClick(R.id.label_forgot_password)
    public void onViewClicked() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        if (ViewUtils.checkEmailValidity(userNameEditText) ||
                userNameEditText.getText().toString().equals("")) {
            intent.putExtra(EMAIL_ADDRESS, userNameEditText.getText().toString());
            startActivity(intent);
        }
        else {
            userNameEditText.setError(getString(R.string.invalid_email));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}