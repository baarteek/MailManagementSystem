package org.example.server;

import org.example.models.Parcel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final ConcurrentHashMap<Integer, PrintWriter> clientConnections = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, Parcel> parcelMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Serwer uruchomiony na porcie " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            new ClientHandler(socket).start();
        }
    }

    public static void addClientConnection(int clientId, PrintWriter out) {
        clientConnections.put(clientId, out);
    }

    public static void removeClientConnection(int clientId) {
        clientConnections.remove(clientId);
    }

    public static void sendNotificationToClient(int clientId, String message) {
        PrintWriter out = clientConnections.get(clientId);
        if (out != null) {
            out.println(message);
        }
    }

    public static void addOrUpdateParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelID(), parcel);
    }

    public static Parcel getParcelById(int parcelId) {
        return parcelMap.get(parcelId);
    }
}
