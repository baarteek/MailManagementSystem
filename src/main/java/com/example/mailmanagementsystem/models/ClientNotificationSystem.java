package com.example.mailmanagementsystem.models;

public class ClientNotificationSystem implements Observer {
    @Override
    public void update(Parcel parcel) {
        // Logika powiadomień, np. wysyłanie wiadomości e-mail lub SMS
        System.out.println("Powiadomienie: Status Twojej przesyłki o numerze " + parcel.getParcelID() + " został zmieniony na: " + parcel.getStatus());
    }
}
