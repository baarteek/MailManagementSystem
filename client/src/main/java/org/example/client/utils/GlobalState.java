package org.example.client.utils;

public class GlobalState {
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        GlobalState.userId = userId;
    }
}

