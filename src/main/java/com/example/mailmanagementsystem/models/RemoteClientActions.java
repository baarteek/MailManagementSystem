package com.example.mailmanagementsystem.models;

public interface RemoteClientActions {
    void loginToSystem(String username, String password);
    void trackParcel(int parcelID);
}
