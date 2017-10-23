package com.clerkiechat.di.component;

import com.clerkiechat.di.module.AppModule;
import com.clerkiechat.ui.userregistration.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Pranav Bhoraskar
 */

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void injectLogin(LoginActivity loginActivity);
}