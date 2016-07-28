/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.data.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;

    @SerializedName("forks_count")
    public int forkCount;

    @SerializedName("watchers")
    public int watchers;

    @SerializedName("clone_url")
    public String cloneUrl;

    @SerializedName("description")
    public String description;
}
