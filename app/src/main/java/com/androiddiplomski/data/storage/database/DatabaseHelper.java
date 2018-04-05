package com.androiddiplomski.data.storage.database;


import com.androiddiplomski.domain.model.FullRecordingInfo;
import com.androiddiplomski.domain.model.RecordInfo;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DatabaseHelper {

    Single<FullRecordingInfo> getFullRecordInfo();

    Completable addNewRecord(RecordInfo recordInfo, double distance);

    Completable addFullRecord(FullRecordingInfo fullRecordingInfo);

}
