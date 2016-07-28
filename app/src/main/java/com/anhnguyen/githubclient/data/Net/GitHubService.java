/**
 * Github Client
 *
 * Created by Hoang Anh on 7/28/16.
 * Copyright (c) 2016 Reyoulive. All rights reserved.
 */
package com.anhnguyen.githubclient.data.Net;

import com.anhnguyen.githubclient.data.model.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubService {

    @GET("orgs/{org}/repos")
    Observable<List<Repo>> getOrganizationRepos(@Path("org") String org);

}
