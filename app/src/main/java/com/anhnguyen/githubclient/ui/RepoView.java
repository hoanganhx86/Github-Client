/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.ui;

import com.anhnguyen.githubclient.data.model.Commit;

import java.util.List;

public interface RepoView extends LoadDataView{

    void onRenderData(List<Commit> commits);
    void onLoadDataFailed(String error);

}
