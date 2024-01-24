package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Serwer uruchomiony na porcie " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            new ClientHandler(socket).start();
        }
    }
}
