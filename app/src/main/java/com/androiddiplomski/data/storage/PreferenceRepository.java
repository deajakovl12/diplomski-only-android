package com.androiddiplomski.data.storage;

public interface PreferenceRepository {

    void setUserId(String userId);

    String getUserId();

    void setLastRecordId(int recordId);

    int getLastRecordId();
}
