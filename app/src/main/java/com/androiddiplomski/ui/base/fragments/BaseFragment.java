package com.androiddiplomski.ui.base.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.androiddiplomski.application.TaskApplication;
import com.androiddiplomski.injection.component.ActivityComponent;
import com.androiddiplomski.ui.base.activities.BaseActivity;


public abstract class BaseFragment extends Fragment {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TaskApplication taskApplication = (TaskApplication) getActivity().getApplication();

        inject(((BaseActivity) getActivity()).getActivityComponent(taskApplication));
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
