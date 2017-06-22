package com.mmh.mapo.domain.repository;

public interface PreferenceRepository {

    void saveAccessToken(String token);
    void saveUserId(String id);
    void saveUserName(String name);
    void saveUserEmail(String email);
    void saveUserImage(String image);

    String getAccessToken();
    String getUserId();
    String getUserName();
    String getUserEmail();
    String getUserImage();

    void clear();
}
