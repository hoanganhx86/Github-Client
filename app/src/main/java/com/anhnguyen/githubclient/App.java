/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient;

import com.anhnguyen.githubclient.di.ApplicationComponent;
import com.anhnguyen.githubclient.di.ApplicationModule;
import com.anhnguyen.githubclient.di.DaggerApplicationComponent;

import android.app.Application;

public class App extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
