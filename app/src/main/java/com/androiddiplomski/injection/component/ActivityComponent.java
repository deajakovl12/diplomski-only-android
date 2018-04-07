package com.androiddiplomski.injection.component;


import com.androiddiplomski.injection.module.ActivityModule;
import com.androiddiplomski.injection.module.PresenterModule;
import com.androiddiplomski.injection.module.RouterModule;
import com.androiddiplomski.injection.scope.ForActivity;
import com.androiddiplomski.ui.base.activities.BaseActivity;
import com.androiddiplomski.ui.home.HomePresenter;
import com.androiddiplomski.ui.home.HomeRouter;
import com.androiddiplomski.ui.login.LoginPresenter;

import dagger.Component;


@ForActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class,
                PresenterModule.class,
                RouterModule.class
        }
)
public interface ActivityComponent extends ActivityComponentActivityInjects, ActivityComponentFragmentsInjects {

    final class Initializer {

        private Initializer() {
        }

        public static ActivityComponent init(final ApplicationComponent applicationComponent, final BaseActivity activity) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

    HomeRouter getHomeRouter();

    HomePresenter getHomePresenter();

    LoginPresenter getLoginPresenter();


}

