package model;
import java.net.*;

public class SendPair extends Thread{
    private Game game;
    private InetAddress ipSend1;
    private InetAddress ipSend2;
    private int port1;
    private int port2;
    private DatagramSocket clientSocket;
    private byte[] sendData;
    private NetworkUser user1;
    private NetworkUser user2;
    private boolean stopGame;
    public SendPair(NetworkUser u1, NetworkUser u2, Game game){
        try {
            user1 = u1;
            user2 = u2;
            ipSend1 = InetAddress.getByName(u1.getIp());
            ipSend2 = InetAddress.getByName(u2.getIp());
            port1 = u1.getPort();
            port2 = u2.getPort();
            this.game = game;
            clientSocket = new DatagramSocket();
            StringBuilder sb = new StringBuilder();
            sb.append(u1.getIp() + " " + port1);
            sendData = sb.toString().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipSend1, port1);
            clientSocket.send(sendPacket);
            sb = new StringBuilder();
            sb.append(u2.getIp() + " " + port2);
            sendData = sb.toString().getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, ipSend2, port2);
            clientSocket.send(sendPacket);
            stopGame = false;
        }
        catch (Exception e) {
            System.out.println("sendPair sendPair "+e.getMessage());
        }
    }
    public void run(){
        try {
            while (!stopGame) {
                StringBuffer str = new StringBuffer();
                str.append(game.getBallX() + " " + game.getBallY() + " " + game.getGamer1() + " " + game.getGamer2() + " " + game.getCount1() + " " + game.getCount2() + " ");
                sendData = new byte[1024];
                sendData = str.toString().getBytes();
                DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, ipSend1, port1);
                DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length, ipSend2, port2);
                clientSocket.send(sendPacket1);
                clientSocket.send(sendPacket2);
            }
        }
        catch (Exception e) {
            System.out.println("sendPair " + e.getMessage());
        }
    }
    public NetworkUser getUser1(){
        return user1;
    }
    public NetworkUser getUser2(){
        return user2;
    }
    public Game getGame(){
        return game;
    }
    public InetAddress getIp1(){
        return ipSend1;
    }
    public InetAddress getIp2(){
        return ipSend2;
    }
    public int getPort1(){
        return port1;
    }
    public int getPort2(){
        return port2;
    }
    public void stopGame(){
        stopGame = true;
    }
}
