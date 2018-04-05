package com.androiddiplomski.ui.home;

import com.androiddiplomski.domain.model.FullRecordingInfo;
import com.androiddiplomski.domain.model.RecordInfo;

public interface HomePresenter {

    void setView(HomeView view);

    void getMovieInfo();

    void dispose();

    void saveFullRecordToDb(FullRecordingInfo fullRecordingInfo);

    void saveRecordToDb(RecordInfo recordInfo, double distance);
}
