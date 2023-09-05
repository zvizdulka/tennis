package model;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientJoin {
    private String ipServer;
    private int portServer;
    private boolean ingame;
    private DatagramSocket clientSocket;
    public ClientJoin(String ip, int port) throws Exception{
        ipServer = ip;
        portServer = port;
        clientSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName(ipServer);
        String str = "join";
        byte[] sendData = str.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portServer);
        clientSocket.send(sendPacket);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        String g = modifiedSentence.substring(0, 7);
        if(g.equals("nogamer")){
            ingame = false;
            clientSocket.close();
        }
        else {
            ingame = true;
        }
    }
    public int getPortServer(){
        return portServer;
    }
    public String getIpServer(){
        return ipServer;
    }
    public boolean inGame(){
        return ingame;
    }
    public DatagramSocket getClientSocket(){
        return clientSocket;
    }
}

