package com.androiddiplomski.ui.home;

import com.androiddiplomski.data.api.models.response.MovieApiResponse;


public interface HomeView {

    void showData(MovieApiResponse movieInfo);
}
