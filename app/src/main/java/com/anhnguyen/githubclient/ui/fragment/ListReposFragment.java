/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.ui.fragment;

import com.anhnguyen.githubclient.R;
import com.anhnguyen.githubclient.RLog;
import com.anhnguyen.githubclient.Utils;
import com.anhnguyen.githubclient.data.model.Repo;
import com.anhnguyen.githubclient.ui.ListReposView;
import com.anhnguyen.githubclient.ui.adapter.ListReposRecyclerAdapter;
import com.anhnguyen.githubclient.ui.presenter.ListReposViewPresenter;
import com.anhnguyen.githubclient.ui.widget.rcv.SpacesItemDecoration;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@FragmentWithArgs
public class ListReposFragment extends BaseFragment implements ListReposView, ListReposRecyclerAdapter.OnItemClickListener{

    private static final String TAG = "ListReposFragment";

    @Inject
    ListReposViewPresenter listReposViewPresenter;

    @Arg
    String orgName;

    View root;
    @Bind(R.id.rcv)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    
    ListReposRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

        initialize();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list_repos, container, false);
        ButterKnife.bind(this, root);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listReposViewPresenter.loadOrganizeRepos(orgName);
                swipeRefreshLayout.setEnabled(false);
            }
        });

        RLog.v(TAG, "onCreateView id " + orgName);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){

        }

        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onRenderData(List<Repo> listRepos) {
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(Utils.isSmallestScreenWidthAtLeast600dp(getActivity())?3:2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Utils.dpToPx(this.getActivity(), Utils.isSmallestScreenWidthAtLeast600dp(getActivity()) ? 12 : 8), Utils.isSmallestScreenWidthAtLeast600dp(getActivity())?3:2);
        recyclerView.addItemDecoration(spacesItemDecoration);

        adapter = new ListReposRecyclerAdapter(getActivity(), listRepos);
        adapter.setAnimationOnScrolling(true);
        adapter.setAnimationOnReverseScrolling(false);
        adapter.setAnimationInterpolator(new DecelerateInterpolator());
        adapter.setAnimationInitialDelay(500L);
        adapter.setAnimationDelay(20L);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
        adapter.setLongPressDragEnabled(true);
    }

    @Override
    public void onLoadDataFailed(String error) {

    }

    @Override
    public void showLoading() {
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setEnabled(false);
        }
    }

    @Override
    public void hideLoading() {
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        showMessage(root, message);
    }

    private void initialize() {
        listReposViewPresenter.setView(this);
        listReposViewPresenter.loadOrganizeRepos(orgName);
    }

    @Override
    public void onContentItemClicked(View view, Repo repo) {
        // TODO : show list commit
    }
}
