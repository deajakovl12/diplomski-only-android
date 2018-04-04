package com.androiddiplomski.data.api.converter;


import com.androiddiplomski.data.api.models.response.MovieApiResponse;
import com.androiddiplomski.domain.model.MovieInfo;

import java.util.List;


public interface MovieAPIConverter {

    List<MovieInfo> convertToMovieInfo(List<MovieApiResponse> movieApiResponse);

}
