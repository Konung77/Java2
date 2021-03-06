package lesson7;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 2019-03-04
 */
public class ClientHandler implements Runnable
{
  private Socket clientSocket;
  private Server server;
  private PrintWriter outMsg;
  private Scanner inMsg;
  private static int clientCount = 0;
  private String nickName;

  public ClientHandler(Socket clientSocket, Server server)
  {
    try
    {
      clientCount++;
      this.clientSocket = clientSocket;
      this.server = server;
      this.nickName = Integer.toString(clientCount);
      this.outMsg = new PrintWriter(clientSocket.getOutputStream());
      this.inMsg = new Scanner(clientSocket.getInputStream());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public ClientHandler(Socket clientSocket, Server server, String nickName)
  {
    try
    {
      clientCount++;
      this.clientSocket = clientSocket;
      this.server = server;
      this.nickName = nickName;
      this.outMsg = new PrintWriter(clientSocket.getOutputStream());
      this.inMsg = new Scanner(clientSocket.getInputStream());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void run()
  {
    try
    {
      server.notificationAllClientWithNewMessage("New client in our chat: " + nickName);
      server.notificationAllClientWithNewMessage("In our chat = " + clientCount + "clients!");


      while (true)
      {
        if (inMsg.hasNext())
        {
          String clientMsg = inMsg.nextLine();
          if (clientMsg.equalsIgnoreCase("QUIT"))
          {
            break;
          }
          String[] words = clientMsg.split(" ", 3);
          if (words[0].equals("/w")) {
            server.notificationClientByNickWithNewMessage(words[1], words[2]);
            System.out.println(words[2]);
          } else {
            System.out.println(clientMsg);
            server.notificationAllClientWithNewMessage(clientMsg);
          }
        }
      }

      Thread.sleep(1000);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      exitFromChat();
    }

  }

  private void exitFromChat()
  {
    clientCount--;
    server.notificationAllClientWithNewMessage("Client exited. In out chat = " + clientCount + " clients!");
    server.removeClient(this);
  }

  public void sendMessage(String msg)
  {
    try
    {
      outMsg.println(msg);
      outMsg.flush();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

}
