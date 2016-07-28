/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.data.Net;

import com.anhnguyen.githubclient.data.model.Repo;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

@Singleton
public class GitHubClientApiImpl implements GitHubClientApi{

    private Context context;

    @Inject
    public GitHubClientApiImpl(Context context){
        this.context = context;
    }

    @Override
    public Observable<List<Repo>> getOrganizationRepos(String org) {
        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Observable<List<Repo>> repos = gitHubService.getOrganizationRepos(org);
        return repos;
    }


}
