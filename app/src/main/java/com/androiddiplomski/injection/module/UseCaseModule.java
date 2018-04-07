package com.androiddiplomski.injection.module;

import com.androiddiplomski.data.service.NetworkService;
import com.androiddiplomski.data.storage.TemplatePreferences;
import com.androiddiplomski.data.storage.database.DatabaseHelper;
import com.androiddiplomski.domain.usecase.LoginUseCase;
import com.androiddiplomski.domain.usecase.LoginUseCaseImpl;
import com.androiddiplomski.domain.usecase.MovieUseCase;
import com.androiddiplomski.domain.usecase.MovieUseCaseImpl;
import com.androiddiplomski.domain.usecase.RecordUseCase;
import com.androiddiplomski.domain.usecase.RecordUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UseCaseModule {


    @Provides
    @Singleton
    MovieUseCase providePersonUseCase(final TemplatePreferences preferences, final NetworkService networkService) {
        return new MovieUseCaseImpl(networkService, preferences);
    }

    @Provides
    @Singleton
    RecordUseCase provideRecordUseCase(final TemplatePreferences preferences,
                                       final NetworkService networkService,
                                       final DatabaseHelper databaseHelper) {
        return new RecordUseCaseImpl(networkService, preferences, databaseHelper);
    }

    @Provides
    @Singleton
    LoginUseCase provideLoginUseCase(final NetworkService networkService) {
        return new LoginUseCaseImpl(networkService);
    }

}
