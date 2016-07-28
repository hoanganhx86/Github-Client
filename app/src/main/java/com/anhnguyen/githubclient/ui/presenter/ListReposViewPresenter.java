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
import com.anhnguyen.githubclient.data.model.Repo;
import com.anhnguyen.githubclient.ui.ListReposView;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListReposViewPresenter implements Presenter{
    public static final String TAG = "ListReposViewPresenter";

    GitHubClientApi gitHubClientApi;
    ListReposView view;
    Subscription subscription;

    @Inject
    public ListReposViewPresenter(GitHubClientApi gitHubClientApi){
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

    public void setView(@NonNull ListReposView view) {
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

    public void loadOrganizeRepos(String org){
        if(TextUtils.isEmpty(org)){
            showErrorMessage("Organization is empty");
            return;
        }

        subscription = gitHubClientApi.getOrganizationRepos(org)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<Repo>>() {

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
                public void onNext(List<Repo> repos) {
                    RLog.d(TAG, "data loaded size " + repos.size());
                    view.onRenderData(repos);
                }
            });
    }

}
