package com.androiddiplomski.data.storage;

import android.content.SharedPreferences;

public final class TemplatePreferences implements PreferenceRepository {

    private static final String KEY_USER_ID = "key_user_id";

    private static final String KEY_LAST_RECORD_ID = "key_last_record_id";

    private static final String EMPTY_STRING = "";

    private final SharedPreferences secureDelegate;


    public static TemplatePreferences create(final SharedPreferences secureDelegate) {
        return new TemplatePreferences(secureDelegate);
    }

    private TemplatePreferences(final SharedPreferences secureDelegate) {
        this.secureDelegate = secureDelegate;
    }

    @Override
    public void setUserId(final String userId) {
        secureDelegate.edit().putString(KEY_USER_ID, userId).apply();
    }

    @Override
    public String getUserId() {
        return secureDelegate.getString(KEY_USER_ID, EMPTY_STRING);
    }

    @Override
    public void setLastRecordId(int recordId) {
        secureDelegate.edit().putInt(KEY_LAST_RECORD_ID, recordId).apply();
    }

    @Override
    public int getLastRecordId() {
        return secureDelegate.getInt(KEY_LAST_RECORD_ID, 0);
    }
}
