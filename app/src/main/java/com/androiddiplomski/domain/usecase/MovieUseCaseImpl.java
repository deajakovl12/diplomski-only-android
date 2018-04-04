package com.androiddiplomski.domain.usecase;


import com.androiddiplomski.data.api.models.response.MovieApiResponse;
import com.androiddiplomski.data.service.NetworkService;
import com.androiddiplomski.data.storage.TemplatePreferences;

import io.reactivex.Single;

public class MovieUseCaseImpl implements MovieUseCase {

    private final NetworkService networkService;

    private final TemplatePreferences preferences;


    public MovieUseCaseImpl(NetworkService networkService, TemplatePreferences preferences) {
        this.networkService = networkService;
        this.preferences = preferences;
    }

    @Override
    public Single<MovieApiResponse> getMovieInfo() {
        return Single
                .defer(() -> networkService.movieInfo());
    }

}
