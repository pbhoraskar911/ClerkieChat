package com.clerkiechat.di;

import android.app.Application;

import com.clerkiechat.di.component.ApplicationComponent;
import com.clerkiechat.di.component.DaggerApplicationComponent;
import com.clerkiechat.di.module.AppModule;

/**
 * Created by Pranav Bhoraskar
 */

public class ClerkieApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}