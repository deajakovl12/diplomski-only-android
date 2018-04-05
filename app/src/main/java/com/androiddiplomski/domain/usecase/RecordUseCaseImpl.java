package com.androiddiplomski.domain.usecase;


import com.androiddiplomski.data.service.NetworkService;
import com.androiddiplomski.data.storage.TemplatePreferences;
import com.androiddiplomski.data.storage.database.DatabaseHelper;
import com.androiddiplomski.domain.model.FullRecordingInfo;
import com.androiddiplomski.domain.model.RecordInfo;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RecordUseCaseImpl implements RecordUseCase{

    private final NetworkService networkService;

    private final TemplatePreferences preferences;

    private final DatabaseHelper databaseHelper;


    public RecordUseCaseImpl(NetworkService networkService,
                             TemplatePreferences preferences,
                             DatabaseHelper databaseHelper) {
        this.networkService = networkService;
        this.preferences = preferences;
        this.databaseHelper = databaseHelper;
    }


    @Override
    public Single<FullRecordingInfo> getFullRecordInfo() {
        return null;
    }

    @Override
    public Completable updateFullRecordInfo(double distance, String trainingId) {
        return null;
    }

    @Override
    public Completable addNewRecord(RecordInfo recordInfo) {
        return Completable.defer(() -> databaseHelper.addNewRecord(recordInfo));
    }

    @Override
    public Completable addFullRecord(FullRecordingInfo fullRecordingInfo) {
        return Completable.defer(() -> databaseHelper.addFullRecord(fullRecordingInfo));
    }
}
