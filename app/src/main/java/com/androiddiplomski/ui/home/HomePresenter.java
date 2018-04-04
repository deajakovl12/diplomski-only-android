package com.androiddiplomski.ui.home;

public interface HomePresenter {

    void setView(HomeView view);

    void getMovieInfo();

    void dispose();
}
