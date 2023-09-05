package model;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientNewGame {
    private String ipServer;
    private int portServer;
    private DatagramSocket clientSocket;
    public ClientNewGame(String ip, int port) throws Exception{
        ipServer = ip;
        portServer = port;
        clientSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName(ipServer);
        String str = "newgame";
        byte[] sendData = str.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portServer);
        clientSocket.send(sendPacket);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
    }
    public int getPortServer(){
        return portServer;
    }
    public String getIpServer(){
        return ipServer;
    }
    public DatagramSocket getClientSocket(){
        return clientSocket;
    }
}

