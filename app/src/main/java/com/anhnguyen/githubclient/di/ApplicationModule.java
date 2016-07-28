/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.di;

import com.anhnguyen.githubclient.App;
import com.anhnguyen.githubclient.data.Net.GitHubClientApi;
import com.anhnguyen.githubclient.data.Net.GitHubClientApiImpl;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final App app;

    public ApplicationModule(App app){
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return this.app.getApplicationContext();
    }

    @Provides
    @Singleton
    GitHubClientApi provideGitHubClient(){
        return new GitHubClientApiImpl(app.getApplicationContext());
    }

}
