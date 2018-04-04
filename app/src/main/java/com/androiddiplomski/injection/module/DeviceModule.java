package com.androiddiplomski.injection.module;

import com.androiddiplomski.application.TaskApplication;
import com.androiddiplomski.device.ApplicationInformation;
import com.androiddiplomski.device.ApplicationInformationImpl;
import com.androiddiplomski.device.DeviceInformation;
import com.androiddiplomski.device.DeviceInformationImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DeviceModule {

    @Provides
    @Singleton
    public DeviceInformation provideDeviceInformation() {
        return new DeviceInformationImpl();
    }

    @Provides
    @Singleton
    public ApplicationInformation provideApplicationInformation(final TaskApplication application) {
        return new ApplicationInformationImpl(application, application.getPackageManager());
    }
}
