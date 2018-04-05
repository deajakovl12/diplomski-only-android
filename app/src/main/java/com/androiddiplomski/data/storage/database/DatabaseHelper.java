package com.androiddiplomski.data.storage.database;


import com.androiddiplomski.domain.model.FullRecordingInfo;
import com.androiddiplomski.domain.model.RecordInfo;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DatabaseHelper {

    Single<FullRecordingInfo> getFullRecordInfo();

    Completable updateFullRecordInfo(double distance, String trainingId);

    Completable addNewRecord(RecordInfo recordInfo);

    Completable addFullRecord(FullRecordingInfo fullRecordingInfo);

}
