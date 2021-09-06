package server;

import model.UserData;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String []args) throws Exception{
        new SocketServer();
    }
    public SocketServer() throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        outStream.writeObject(new UserData());
        System.out.println("Created object");

        if (inStream.readObject().getClass().getSimpleName().equals("UserData")){
            UserData receivedObject = (UserData)inStream.readObject();
            outStream.writeChars("Data received");
            ServerProtocol protocol = new ServerProtocol();
            protocol.setUserData(receivedObject);
            outStream.writeChars("Innovate☺");
        }else {
            outStream.writeChars("Data format invalid. Please resend");
        }
    }
}
