package com.androiddiplomski.application;

import android.app.Application;

import com.androiddiplomski.injection.ComponentFactory;
import com.androiddiplomski.injection.component.ApplicationComponent;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

public final class TaskApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
        Timber.plant(new Timber.DebugTree());
        Stetho.initializeWithDefaults(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
