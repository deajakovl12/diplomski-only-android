package com.androiddiplomski.injection.module;

import com.androiddiplomski.data.service.NetworkService;
import com.androiddiplomski.data.storage.TemplatePreferences;
import com.androiddiplomski.domain.usecase.MovieUseCase;
import com.androiddiplomski.domain.usecase.MovieUseCaseImpl;

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

}
