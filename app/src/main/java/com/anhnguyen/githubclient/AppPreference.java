/**
 * Ryoulive
 *
 * Created by Hoang Anh on 6/6/16.
 * Copyright (c) 2016 Reyoulive. All rights reserved.
 */
package com.anhnguyen.githubclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreference {

    private SharedPreferences sharedPreferences;

    @Inject
    public AppPreference(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
