package com.clerkiechat.ui.userregistration.login;

import com.clerkiechat.ui.userregistration.ViewInterface;

/**
 * Created by Pranav Bhoraskar
 */

public interface LoginViewInterface extends ViewInterface {

    void showProgressBar();

    void hideProgressBar();

    void openChatHome();

    void onCredentialsFailure();
}
