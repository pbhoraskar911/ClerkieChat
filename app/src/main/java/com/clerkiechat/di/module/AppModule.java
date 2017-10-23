package com.clerkiechat.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Pranav Bhoraskar
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public Application providesApplication() {
        return mApplication;
    }
}
