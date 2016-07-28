/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.ui;

import com.anhnguyen.githubclient.data.model.Repo;

import java.util.List;

public interface ListReposView extends LoadDataView{

    void onRenderData(List<Repo> listRepos);
    void onLoadDataFailed(String error);

}
