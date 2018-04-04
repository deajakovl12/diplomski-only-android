package com.androiddiplomski.data.service;


import com.androiddiplomski.data.api.models.response.MovieApiResponse;

import io.reactivex.Single;

public interface NetworkService {

    Single<MovieApiResponse> movieInfo();
}
