package com.mmh.mapo.domain.repository.impl;

import android.content.Context;


import com.mmh.mapo.core.android.App;
import com.mmh.mapo.core.android.BasePreference;
import com.mmh.mapo.domain.repository.PreferenceRepository;

import javax.inject.Inject;



/**
 * Created by vladimir on 07.06.16.
 */
public class PreferenceRepositoryImpl extends BasePreference implements PreferenceRepository {
    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String PREF_USER_ID = "PREF_USER_ID";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";
    private static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";
    private static final String PREF_USER_PHOTO = "PREF_USER_PHOTO";

    private App app;

    @Inject
    public PreferenceRepositoryImpl(App app) {
        this.app = app;
    }

    @Override
    protected Context getContext() {
        return app;
    }

    @Override
    public void saveAccessToken(String token) {
        save(PREF_ACCESS_TOKEN, token);
    }

    @Override
    public void saveUserId(String id) {
        save(PREF_USER_ID, id);
    }

    @Override
    public void saveUserName(String name) {
        save(PREF_USER_NAME, name);
    }

    @Override
    public void saveUserEmail(String email) {
        save(PREF_USER_EMAIL, email);
    }

    @Override
    public void saveUserImage(String image) {
        save(PREF_USER_PHOTO, image);
    }

    @Override
    public String getAccessToken() {
        return getStr(PREF_ACCESS_TOKEN);
    }

    @Override
    public String getUserId() {
        return getStr(PREF_USER_ID);
    }

    @Override
    public String getUserName() {
        return getStr(PREF_USER_NAME);
    }

    @Override
    public String getUserEmail() {
        return getStr(PREF_USER_EMAIL);
    }

    @Override
    public String getUserImage() {
        return getStr(PREF_USER_PHOTO);
    }

    @Override
    public void clear() {
        getPreference().edit().clear().apply();
    }
}
