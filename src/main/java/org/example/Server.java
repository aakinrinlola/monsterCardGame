package org.example;

import org.example.controller.UserController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;

    public void start() {
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server started on port 8000...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     OutputStream out = clientSocket.getOutputStream()) {

                    // Read and process the request
                    String request = readRequest(in);
                    String requestBody = readRequestBody(in);
                    String response = handleRequest(request, requestBody);

                    // Send response
                    out.write(response.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readRequest(BufferedReader in) throws IOException {
        StringBuilder request = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            request.append(line).append("\n");
        }
        return request.toString();
    }

    private String readRequestBody(BufferedReader in) throws IOException {
        StringBuilder body = new StringBuilder();
        while (in.ready()) {
            body.append((char) in.read());
        }
        return body.toString();
    }

    private String handleRequest(String request, String requestBody) {
        UserController userController = new UserController();

        if (request.startsWith("POST /register")) {
            return userController.register(requestBody);
        } else if (request.startsWith("POST /login")) {
            return userController.login(requestBody);
        } else {
            return "HTTP/1.1 404 Not Found\r\n\r\n";
        }
    }

    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server stopped.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}