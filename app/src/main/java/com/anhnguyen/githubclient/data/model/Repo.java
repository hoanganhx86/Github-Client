/**
 * Github Client
 *
 * Created by Anh Nguyen on 7/28/16.
 * Copyright (c) 2016 Anh Nguyen. All rights reserved.
 */
package com.anhnguyen.githubclient.data.model;

import com.google.gson.annotations.SerializedName;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class Repo implements IFlexible<RecyclerView.ViewHolder> {

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

    public static class RepoComparator implements Comparator<Repo> {

        @Override
        public int compare(Repo lhs, Repo rhs) {
            return rhs.forkCount - lhs.forkCount;
        }
    }



    /*---------------*/
	/* Util METHODS for working with flexible adapter */
	/*---------------*/
    private static final String MAPPING_ILLEGAL_STATE = " is not implemented. If you want FlexibleAdapter creates and binds ViewHolder for you, you must override and implement the method ";
    /* Item flags recognized by the FlexibleAdapter */
    protected boolean mEnabled = true, mHidden = false,
        mSelectable = true, mDraggable = true, mSwipeable = true;

    /*---------------*/
	/* BASIC METHODS */
	/*---------------*/

    /**
     * You <b>must</b> implement this method to compare items identifiers.
     * <p>Adapter needs this method to distinguish them and pick up correct items.</p>
     * See <a href="http://developer.android.com/reference/java/lang/Object.html#writing_equals">
     * Writing a correct {@code equals} method</a> to implement your own {@code equals} method.
     * <p>Basic Java implementation:
     * <pre>
     * public boolean equals(Object o) {
     *     return this == o;
     * }</pre></p>
     * <p>When used with {@code HashMap}, the general contract for the {@code equals} and
     * {@link #hashCode()} methods is that if {@code equals} returns {@code true} for any two
     * objects, then {@code hashCode()} must return the same value for these objects. This means
     * that subclasses of {@code Object} usually override either both methods or neither of them.
     *
     * @param o instance to compare
     * @return true if items are equals, false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repo that = (Repo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    @Override
    public boolean isHidden() {
        return mHidden;
    }

    @Override
    public void setHidden(boolean hidden) {
        mHidden = hidden;
    }

	/*--------------------*/
	/* SELECTABLE METHODS */
	/*--------------------*/

    @Override
    public boolean isSelectable() {
        return mSelectable;
    }

    @Override
    public void setSelectable(boolean selectable) {
        this.mSelectable = selectable;
    }

	/*-------------------*/
	/* TOUCHABLE METHODS */
	/*-------------------*/

    @Override
    public boolean isDraggable() {
        return mDraggable;
    }

    @Override
    public void setDraggable(boolean draggable) {
        mDraggable = draggable;
    }

    @Override
    public boolean isSwipeable() {
        return mSwipeable;
    }

    @Override
    public void setSwipeable(boolean swipeable) {
        mSwipeable = swipeable;
    }

	/*---------------------*/
	/* VIEW HOLDER METHODS */
	/*---------------------*/

    @Override
    public int getLayoutRes() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if called but not implemented
     */
    @Override
    public RecyclerView.ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        throw new IllegalStateException("onCreateViewHolder()" + MAPPING_ILLEGAL_STATE
            + this.getClass().getSimpleName() + ".createViewHolder().");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if called but not implemented
     */
    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        throw new IllegalStateException("onBindViewHolder()" + MAPPING_ILLEGAL_STATE
            + this.getClass().getSimpleName() + ".bindViewHolder().");
    }


}
