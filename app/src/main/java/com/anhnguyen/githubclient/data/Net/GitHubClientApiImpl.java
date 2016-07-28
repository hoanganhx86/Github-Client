/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.data.Net;

import com.anhnguyen.githubclient.data.model.Commit;
import com.anhnguyen.githubclient.data.model.Repo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

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
        // RLog.d("GitHubClientApiImpl", "GitHubClientApiImpl load repos org " + org + " count " + repos.map(retRepos -> retRepos.size()));
        return repos;
    }

    @Override
    public Observable<List<Commit>> getRepoListCommits(String org, final String repo) {
        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Observable<List<GitHubService.RepoCommitsResponse>> commitContainers = gitHubService.getOrganizationRepos(org, repo);

        // transform responses data to commits
        return commitContainers.map(new Func1<List<GitHubService.RepoCommitsResponse>, List<Commit>>() {
            @Override
            public List<Commit> call(List<GitHubService.RepoCommitsResponse> repoCommitsResponses) {
                List<Commit> ret = new ArrayList<Commit>();
                for(GitHubService.RepoCommitsResponse repoCommitsResponse : repoCommitsResponses){
                    Commit commit = repoCommitsResponse.commit;
                    commit.sha = repoCommitsResponse.sha;
                    ret.add(commit);
                }
                return  ret;
            }
        });

    }


}
