package com.anhnguyen.githubclient.ui.activity;

import com.anhnguyen.githubclient.R;
import com.anhnguyen.githubclient.RLog;
import com.anhnguyen.githubclient.Utils;
import com.anhnguyen.githubclient.data.model.Commit;
import com.anhnguyen.githubclient.ui.RepoView;
import com.anhnguyen.githubclient.ui.adapter.ListCommitsRecyclerAdapter;
import com.anhnguyen.githubclient.ui.presenter.RepoViewPresenter;
import com.anhnguyen.githubclient.ui.widget.rcv.SpacesItemDecoration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;

public class RepoCommitsActivity extends BaseActivity implements RepoView {

    public static final String EXTRA_ORG_NAME = "EXTRA_ORG_NAME";
    public static final String EXTRA_REPO_NAME = "EXTRA_REPO_NAME";

    private static final String TAG = "RepoCommitsActivity";

    @Inject
    RepoViewPresenter repoViewPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rcv)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    String orgName;
    String repoName;
    ListCommitsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_commits);

        this.getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repoViewPresenter.setView(this);

        final Intent intent = getIntent();
        if(intent != null){
            orgName = intent.getStringExtra(EXTRA_ORG_NAME);
            repoName = intent.getStringExtra(EXTRA_REPO_NAME);

            toolbar.setTitle(repoName);
        }

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        RLog.d(TAG, "org" + orgName + " repo " + repoName);
        showLoading();
        repoViewPresenter.loadRepoCommits(orgName, repoName);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoViewPresenter.loadRepoCommits(orgName, repoName);
                swipeRefreshLayout.setEnabled(false);
            }
        });

    }


    @Override
    public void onRenderData(List<Commit> commits) {
        //final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(Utils.isSmallestScreenWidthAtLeast600dp(getActivity())?3:2, StaggeredGridLayoutManager.VERTICAL);
        SmoothScrollLinearLayoutManager smoothScrollLinearLayoutManager = new SmoothScrollLinearLayoutManager(this);
        recyclerView.setLayoutManager(smoothScrollLinearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(
            Utils.dpToPx(this, Utils.isSmallestScreenWidthAtLeast600dp(this) ? 12 : 8), Utils.isSmallestScreenWidthAtLeast600dp(this)?3:2);
        recyclerView.addItemDecoration(spacesItemDecoration);

        adapter = new ListCommitsRecyclerAdapter(this, commits);
        adapter.setAnimationOnScrolling(true);
        adapter.setAnimationOnReverseScrolling(false);
        adapter.setAnimationInterpolator(new DecelerateInterpolator());
        adapter.setAnimationInitialDelay(500L);
        adapter.setAnimationDelay(20L);

        recyclerView.setAdapter(adapter);
        adapter.setLongPressDragEnabled(true);
    }

    @Override
    public void onLoadDataFailed(String error) {

    }

    @Override
    public void showLoading() {
        RLog.d(TAG, "showLoading");
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    swipeRefreshLayout.setEnabled(false);
                }
            });

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
        //showMessage(root, message);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
