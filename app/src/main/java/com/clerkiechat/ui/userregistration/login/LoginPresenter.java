package com.clerkiechat.ui.userregistration.login;

import android.support.annotation.NonNull;

import com.clerkiechat.ui.userregistration.Presenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

/**
 * Created by Pranav Bhoraskar
 */

public class LoginPresenter implements Presenter<LoginViewInterface> {

    private LoginViewInterface loginViewInterface;

    @Inject
    public LoginPresenter() {
    }

    void checkCredentialsValidity(FirebaseAuth firebaseAuth, String userName, String password) {
        if (loginViewInterface != null) {
            loginViewInterface.showProgressBar();
        }

        performLogin(firebaseAuth, userName, password);
    }

    private void performLogin(FirebaseAuth firebaseAuth, String userName, String password) {
        firebaseAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener((LoginActivity) loginViewInterface.getContext(),
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    onSuccess();
                                }
                                else {
                                    credentialsFailure();
                                }
                            }
                        });
    }

    public void onSuccess() {
        if (loginViewInterface != null) {
            loginViewInterface.openChatHome();
        }
    }

    @Override
    public void attachView(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
    }

    @Override
    public void detachView() {
        this.loginViewInterface = null;
    }

    public void credentialsFailure() {
        if (loginViewInterface != null) {
            loginViewInterface.onCredentialsFailure();
            loginViewInterface.hideProgressBar();
        }
    }
}
