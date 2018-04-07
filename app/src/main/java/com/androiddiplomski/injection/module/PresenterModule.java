package com.androiddiplomski.injection.module;

import com.androiddiplomski.data.api.converter.MovieAPIConverter;
import com.androiddiplomski.domain.usecase.LoginUseCase;
import com.androiddiplomski.domain.usecase.MovieUseCase;
import com.androiddiplomski.domain.usecase.RecordUseCase;
import com.androiddiplomski.injection.scope.ForActivity;
import com.androiddiplomski.manager.StringManager;
import com.androiddiplomski.ui.home.HomePresenter;
import com.androiddiplomski.ui.home.HomePresenterImpl;
import com.androiddiplomski.ui.login.LoginPresenter;
import com.androiddiplomski.ui.login.LoginPresenterImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

import static com.androiddiplomski.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static com.androiddiplomski.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


@Module
public final class PresenterModule {

    @ForActivity
    @Provides
    HomePresenter provideHomePresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler,
                                       @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler,
                                       MovieUseCase movieUseCase,
                                       MovieAPIConverter movieAPIConverter,
                                       StringManager stringManager,
                                       RecordUseCase recordUseCase) {
        return new HomePresenterImpl(subscribeScheduler,
                observeScheduler,
                movieUseCase,
                movieAPIConverter,
                stringManager,
                recordUseCase);
    }

    @ForActivity
    @Provides
    LoginPresenter provideLoginPresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler,
                                         @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, LoginUseCase loginUseCase) {
        return new LoginPresenterImpl(subscribeScheduler, observeScheduler, loginUseCase);
    }

}
