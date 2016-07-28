/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.di;

import com.anhnguyen.githubclient.AppPreference;
import com.anhnguyen.githubclient.data.Net.GitHubClientApi;
import com.anhnguyen.githubclient.ui.activity.BaseActivity;
import com.anhnguyen.githubclient.ui.activity.MainActivity;
import com.anhnguyen.githubclient.ui.fragment.BaseFragment;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);

    void inject(MainActivity activity);

    Context context();
    AppPreference preference();
    GitHubClientApi gitHubClientApi();

}
