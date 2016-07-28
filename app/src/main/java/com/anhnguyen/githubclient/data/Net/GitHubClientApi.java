/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.data.Net;

import com.anhnguyen.githubclient.data.model.Repo;

import java.util.List;

import rx.Observable;

public interface GitHubClientApi {

    public static final String BASE_URL = "https://api.github.com";

    Observable<List<Repo>> getOrganizationRepos(String org);

}
