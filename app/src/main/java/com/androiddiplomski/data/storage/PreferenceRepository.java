package com.androiddiplomski.data.storage;

public interface PreferenceRepository {

    void setApiKey(String apyKey);

    String getApyKey();

    void setUserId(long userId);

    long getUserId();

}
