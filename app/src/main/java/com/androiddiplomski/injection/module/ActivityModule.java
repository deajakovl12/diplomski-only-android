package com.androiddiplomski.injection.module;

import android.app.Activity;

import com.androiddiplomski.injection.scope.ForActivity;
import com.androiddiplomski.ui.base.activities.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(final BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @ForActivity
    @Provides
    public Activity provideActivity() {
        return baseActivity;
    }
}
