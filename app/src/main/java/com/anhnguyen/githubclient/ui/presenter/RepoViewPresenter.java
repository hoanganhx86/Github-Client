/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.ui.presenter;


import com.anhnguyen.githubclient.Config;
import com.anhnguyen.githubclient.RLog;
import com.anhnguyen.githubclient.data.Net.GitHubClientApi;
import com.anhnguyen.githubclient.data.model.Commit;
import com.anhnguyen.githubclient.ui.RepoView;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepoViewPresenter implements Presenter{
    public static final String TAG = "RepoViewPresenter";

    GitHubClientApi gitHubClientApi;
    RepoView view;
    Subscription subscription;

    @Inject
    public RepoViewPresenter(GitHubClientApi gitHubClientApi){
        this.gitHubClientApi = gitHubClientApi;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
            subscription = null;
        }
    }

    public void setView(@NonNull RepoView view) {
        this.view = view;
    }

    private void showViewLoading() {
        this.view.showLoading();
    }

    private void hideViewLoading() {
        this.view.hideLoading();
    }

    private void showViewRetry() {
        this.view.showRetry();
    }

    private void hideViewRetry() {
        this.view.hideRetry();
    }

    private void showErrorMessage(String error) {
        this.view.showError(error);
    }

    public void loadRepoCommits(String org, String repo){
        showViewLoading();
        if(TextUtils.isEmpty(org)){
            showErrorMessage("Organization is empty");
            return;
        }

        subscription = gitHubClientApi.getRepoListCommits(org, repo)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<Commit>>() {

                @Override
                public void onCompleted() {
                    RLog.d(TAG, "onCompleted");
                    hideViewLoading();
                }

                @Override
                public void onError(Throwable e) {
                    RLog.d(TAG, "onError " + e.getMessage());
                    if(Config.DEBUG) showErrorMessage(e.getMessage());
                    hideViewLoading();
                }

                @Override
                public void onNext(List<Commit> commits) {
                    RLog.d(TAG, "data loaded size " + commits.size());
                    view.onRenderData(commits);
                }
            });
    }

}
