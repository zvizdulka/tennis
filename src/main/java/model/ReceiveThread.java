package model;
import view.Menu;
import java.net.*;
import java.lang.*;
import java.util.Scanner;

public class ReceiveThread extends Thread {
    private DatagramSocket serverSocket;
    private Menu game;
    public ReceiveThread(DatagramSocket s, Menu game) {
        serverSocket = s;
        this.game = game;
    }
    public void run() {
        try {
            while(true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                String g = sentence.substring(0, 4);
                if(g.equals("end")){
                    serverSocket.close();
                    break;
                }
                else {
                    Scanner sc = new Scanner(sentence);
                    int bx = sc.nextInt();
                    int by = sc.nextInt();
                    int y1 = sc.nextInt();
                    int y2 = sc.nextInt();
                    int c1 = sc.nextInt();
                    int c2 = sc.nextInt();
                    game.set(bx, by, y1, y2, c1, c2);
                }
            }
        }
        catch (Exception e) {
            System.out.println("ReceiveThread " + e.getMessage());
        }
    }
}
