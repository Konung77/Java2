package lesson7;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 2019-03-04
 */
public class Server
{
  private List<ClientHandler> clientHandlers = new ArrayList<>();

  public Server()
  {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    try
    {
      serverSocket = new ServerSocket(8888);
      System.out.println("Server launched");

      while (true)
      {
        clientSocket = serverSocket.accept();
        ClientHandler client = new ClientHandler(clientSocket, this);
        clientHandlers.add(client);
        new Thread(client).start();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        serverSocket.close();
        clientSocket.close();
        System.out.println("Server finished");
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }


  }

  public void notificationAllClientWithNewMessage(String msg)
  {
    for (ClientHandler clientHandler : clientHandlers)
    {
      clientHandler.sendMessage(msg);
    }

  }

  public void removeClient(ClientHandler clientHandler)
  {
    clientHandlers.remove(clientHandler);
  }

  // 2. * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет»,
  // то только клиенту с ником nick3 должно прийти сообщение «Привет»
  public boolean notificationClientByNickWithNewMessage(String nick, String msg)
  {
    for (ClientHandler clientHandler : clientHandlers)
    {
      if (clientHandler.getNickName() == nick) {
        clientHandler.sendMessage(msg);
        return true;
      }
    }
    return false; //Не удалось отправить сообщение, нет такого ника
  }
}
