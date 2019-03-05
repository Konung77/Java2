package lesson6;

import java.io.*;
import java.net.Socket;

public class MyClient {
    private static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static String strFromServer;
    private static BufferedReader reader;
    private static String message;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 2323);
            System.out.println("Client has connected to server");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Client> ");
                message = reader.readLine();
                outputStream.writeUTF(message);
                if (message.equalsIgnoreCase("end")) {
                    System.out.println("Client closed connection");
                    break;
                }
                strFromServer = inputStream.readUTF();
                System.out.println("Server> " + strFromServer);
                if (strFromServer.equalsIgnoreCase("end")) {
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
