package com.androiddiplomski.injection.component;

import com.androiddiplomski.application.TaskApplication;
import com.androiddiplomski.data.api.converter.MovieAPIConverter;
import com.androiddiplomski.data.service.NetworkService;
import com.androiddiplomski.data.storage.TemplatePreferences;
import com.androiddiplomski.data.storage.database.DatabaseHelper;
import com.androiddiplomski.device.ApplicationInformation;
import com.androiddiplomski.device.DeviceInformation;
import com.androiddiplomski.domain.usecase.MovieUseCase;
import com.androiddiplomski.domain.usecase.RecordUseCase;
import com.androiddiplomski.injection.module.ApiModule;
import com.androiddiplomski.injection.module.ApplicationModule;
import com.androiddiplomski.injection.module.DataModule;
import com.androiddiplomski.injection.module.DeviceModule;
import com.androiddiplomski.injection.module.ManagerModule;
import com.androiddiplomski.injection.module.ThreadingModule;
import com.androiddiplomski.injection.module.UseCaseModule;
import com.androiddiplomski.manager.StringManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

import static com.androiddiplomski.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static com.androiddiplomski.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                ManagerModule.class,
                DataModule.class,
                ThreadingModule.class,
                UseCaseModule.class,
                DeviceModule.class
        }
)

public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        private Initializer() {
        }

        public static ApplicationComponent init(final TaskApplication taskApplication) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(taskApplication))
                    .apiModule(new ApiModule())
                    .build();
        }
    }

    @Named(OBSERVE_SCHEDULER)
    Scheduler getObserveScheduler();

    @Named(SUBSCRIBE_SCHEDULER)
    Scheduler getSubscribeScheduler();

    StringManager getStringManager();

    MovieUseCase getMovieUseCase();

    OkHttpClient getOkHttpClient();

    DeviceInformation getDeviceInformation();

    ApplicationInformation getApplicationInformation();

    MovieAPIConverter getMovieApiConverter();

    TemplatePreferences getTemplatePreferences();

    NetworkService getNetworkService();

    DatabaseHelper getDatabaseHelper();

    RecordUseCase getRecordUseCase();

}
