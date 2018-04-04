package com.androiddiplomski.injection.module;

import android.app.Activity;

import com.androiddiplomski.injection.scope.ForActivity;
import com.androiddiplomski.ui.home.HomeRouter;
import com.androiddiplomski.ui.home.HomeRouterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class RouterModule {

    @ForActivity
    @Provides
    HomeRouter provideHomeRouter(final Activity activity) {
        return new HomeRouterImpl(activity);
    }

}
