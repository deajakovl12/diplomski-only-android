package com.androiddiplomski.injection.module;

import com.androiddiplomski.application.TaskApplication;
import com.androiddiplomski.manager.StringManager;
import com.androiddiplomski.manager.StringManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ManagerModule {

    @Provides
    @Singleton
    StringManager provideStringManager(final TaskApplication application) {
        return new StringManagerImpl(application.getResources());
    }
}
