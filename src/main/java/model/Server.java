package model;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;

public class Server extends Thread{
    private DatagramSocket serverSocket;
    private Vector<NetworkUser> users;
    private Vector<SendPair> pairs;
    FileWriter file;
    public Server(){
        try {
            serverSocket = new DatagramSocket(8080);
            users = new Vector<>();
            pairs = new Vector<>();
            file = new FileWriter("./LOG.txt");
            file.write("---NEW SESSION---");
        }
        catch (Exception e) {
            System.out.println("serverconstraction " + e.getMessage());
        }
    }
    public void run() {
        try {
            while(true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                String a = sentence.substring(0, 7);
                String b = sentence.substring(0, 4);
                InetAddress IPAddressSend = receivePacket.getAddress();
                String ip = IPAddressSend.toString();
                String ipClient = ip.substring(1);
                int portSend = receivePacket.getPort();
                if(!a.equals("newgame") && !b.equals("join")) {
                    workWithRecData(ipClient, portSend, sentence);
                }
                if (a.equals("newgame")) {
                    NetworkUser u = new NetworkUser(ipClient, portSend);
                    users.add(u);
                    file.write("new game from " + ipClient + " " + portSend + "\n");
                    file.flush();
                    System.out.println("new game from " + ipClient + " " + portSend);
                }
                if (b.equals("join")) {
                    if (users.size() != 0) {
                        NetworkUser u = new NetworkUser(ipClient, portSend);
                        SendPair p = new SendPair(users.get(0), u, new Game());
                        pairs.add(p);
                        users.remove(0);
                        file.write("join " + ipClient + " " + portSend+ "\n");
                        file.flush();
                        System.out.println("join " + ipClient + " " + portSend);
                        p.start();
                    }
                    else{
                        DatagramSocket clientSocket = new DatagramSocket();
                        String s = "nogamer";
                        byte[] sendData = s.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressSend, portSend);
                        clientSocket.send(sendPacket);
                        clientSocket.close();
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("server " + e.getMessage());
        }
    }
    public void workWithRecData(String ipClient, int port,  String sentence) throws Exception {
        String str = sentence.substring(0, 1);
        NetworkUser r = new NetworkUser(ipClient, port);
        for(int i = 0; i < pairs.size(); i++) {
            if(r.isThisUser(pairs.get(i).getUser1())){
                if (str.equals("0")) {
                    pairs.get(i).stopGame();
                    DatagramSocket clientSocket = new DatagramSocket();
                    String s = "end";
                    byte[] sendData = s.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, pairs.get(i).getIp2(), pairs.get(i).getPort2());
                    clientSocket.send(sendPacket);
                    clientSocket.close();
                    pairs.remove(i);
                    break;
                }
                if (str.equals("1")) {
                    pairs.get(i).getGame().Up1();
                }
                if (str.equals("2")) {
                    pairs.get(i).getGame().Down1();
                }
                if (str.equals("3")) {
                    pairs.get(i).getGame().setTime();
                }
                break;
            }
            if(r.isThisUser(pairs.get(i).getUser2())){
                if (str.equals("0")) {
                    pairs.get(i).stopGame();
                    DatagramSocket clientSocket = new DatagramSocket();
                    String s = "end";
                    byte[] sendData = s.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, pairs.get(i).getIp1(), pairs.get(i).getPort1());
                    clientSocket.send(sendPacket);
                    clientSocket.close();
                    pairs.remove(i);
                    break;
                }
                if (str.equals("1")) {
                    pairs.get(i).getGame().Up2();
                }
                if (str.equals("2")) {
                    pairs.get(i).getGame().Down2();
                }
                if (str.equals("3")) {
                    pairs.get(i).getGame().setTime();
                }
                break;
            }
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
