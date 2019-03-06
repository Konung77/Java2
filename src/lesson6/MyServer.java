package lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    static String strFromClient;
    static String message;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2323)) {
            System.out.println("Server is running");
            Socket socket = serverSocket.accept();
            System.out.println("Client has connected");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                strFromClient = inputStream.readUTF();
                System.out.println("Client> " + strFromClient);
                if (strFromClient.equalsIgnoreCase("end")) {
                    System.out.println("Client closed connection");
                    break;
                }
                System.out.print("Server> ");
                message = reader.readLine();
                outputStream.writeUTF(message);
                if (message.equalsIgnoreCase("end")) {
                    System.out.println("Server closed connection");
                    break;
                }
            }
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
