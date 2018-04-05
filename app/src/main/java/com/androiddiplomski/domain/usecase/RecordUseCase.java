package com.androiddiplomski.domain.usecase;


import com.androiddiplomski.domain.model.FullRecordingInfo;
import com.androiddiplomski.domain.model.RecordInfo;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface RecordUseCase {

    Single<FullRecordingInfo> getFullRecordInfo();

    Completable updateFullRecordInfo(double distance, String trainingId);

    Completable addNewRecord(RecordInfo recordInfo);

    Completable addFullRecord(FullRecordingInfo fullRecordingInfo);

}
