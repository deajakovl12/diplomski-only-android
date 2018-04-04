package com.androiddiplomski.ui.home;

import com.androiddiplomski.R;
import com.androiddiplomski.data.api.converter.MovieAPIConverter;
import com.androiddiplomski.data.api.models.response.MovieApiResponse;
import com.androiddiplomski.domain.model.MovieInfo;
import com.androiddiplomski.domain.usecase.MovieUseCase;
import com.androiddiplomski.manager.StringManager;
import com.androiddiplomski.ui.base.presenter.BasePresenter;

import java.util.List;

import javax.inject.Named;


import io.reactivex.Scheduler;
import timber.log.Timber;

import static com.androiddiplomski.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static com.androiddiplomski.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


public final class HomePresenterImpl extends BasePresenter implements HomePresenter {

    private HomeView view;

    private final MovieUseCase movieUseCase;

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final MovieAPIConverter movieAPIConverter;

    private final StringManager stringManager;

    public HomePresenterImpl(@Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                             @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, final MovieUseCase movieUseCase,
                             final MovieAPIConverter movieAPIConverter, final StringManager stringManager) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.movieUseCase = movieUseCase;
        this.movieAPIConverter = movieAPIConverter;
        this.stringManager = stringManager;
    }

    @Override
    public void setView(final HomeView view) {
        this.view = view;
    }

    @Override
    public void getMovieInfo() {
        if (view != null) {
            addDisposable(movieUseCase.getMovieInfo()
                                      .subscribeOn(subscribeScheduler)
                                      .observeOn(observeScheduler)
                                      .subscribe(this::onGetMovieInfoSuccess, this::onGetMovieInfoFailure));
        }
    }

    private void onGetMovieInfoSuccess(MovieApiResponse movieApiResponses) {
        if (view != null) {
            view.showData(movieApiResponses);
        }
    }

    private void onGetMovieInfoFailure(final Throwable throwable) {
        Timber.e(stringManager.getString(R.string.fetch_movie_info_error), throwable);
    }

    private void onGetMovieInfoSuccess(final List<MovieInfo> movieInfo) {
        if (view != null) {
           // view.showData(movieInfo);
        }
    }
}
