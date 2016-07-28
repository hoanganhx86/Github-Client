package com.anhnguyen.githubclient.ui.fragment;

import com.anhnguyen.githubclient.App;
import com.anhnguyen.githubclient.AppPreference;
import com.anhnguyen.githubclient.di.ApplicationComponent;
import com.hannesdorfmann.fragmentargs.FragmentArgs;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment{

    @Inject
    AppPreference preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        FragmentArgs.inject(this); // read @Arg fields
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((App) getActivity().getApplication()).getApplicationComponent();
    }

    void showMessage(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

}
