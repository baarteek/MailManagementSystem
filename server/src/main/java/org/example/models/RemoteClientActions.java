package org.example.models;

public interface RemoteClientActions {
    void loginToSystem(String username, String password);
    void trackParcel(int parcelID);
}
