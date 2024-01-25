package org.example.models;

import static org.example.server.Server.sendNotificationToClient;

public class ClientNotificationSystem implements Observer {

    @Override
    public void update(Parcel parcel) {
        System.out.println("Powaidomienie obserwatora");
        String message = "Powiadomienie: Status Twojej przesyłki o numerze " + parcel.getParcelID() + " został zmieniony na: " + parcel.getStatus();
        System.out.println("Wiadomosc do klienta: " + parcel.getSender().getClientID());
        sendNotificationToClient(parcel.getSender().getClientID(), message);
    }
}
