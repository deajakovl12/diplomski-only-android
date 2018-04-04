package com.androiddiplomski.domain.usecase;


import com.androiddiplomski.data.api.models.response.MovieApiResponse;

import io.reactivex.Single;

public interface MovieUseCase {

    Single<MovieApiResponse> getMovieInfo();

}
