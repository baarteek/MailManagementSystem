package org.example.client.utils;

import java.io.*;
import java.net.Socket;

public class ServerClient {
    private String serverAddress = "localhost";
    private int serverPort = 1234;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;


    public void connect() {
        try {
            socket = new Socket(serverAddress, serverPort);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        if (out != null) {
            out.println(command);
        }
    }

    public String receiveResponse() {
        try {
            if (in != null) {
                return in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

