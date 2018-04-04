package com.androiddiplomski.injection;


import com.androiddiplomski.application.TaskApplication;
import com.androiddiplomski.injection.component.ActivityComponent;
import com.androiddiplomski.injection.component.ApplicationComponent;
import com.androiddiplomski.ui.base.activities.BaseActivity;

public final class ComponentFactory {

    private ComponentFactory() { }

    public static ApplicationComponent createApplicationComponent(final TaskApplication application) {
        return ApplicationComponent.Initializer.init(application);
    }

    public static ActivityComponent createActivityComponent(final TaskApplication application, final BaseActivity activity) {
        return ActivityComponent.Initializer.init(application.getApplicationComponent(), activity);
    }
}
