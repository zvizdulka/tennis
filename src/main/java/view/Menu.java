package view;
import model.Records;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Menu extends JPanel implements ActionListener, KeyListener {
    private int gamer1 = 300, gamer2 = 300, ballX = 400, ballY = 300;
    private int count1last = 0;
    private int count2last = 0;
    private int count1 = 0;
    private int count2 = 0;
    private Font font;
    private Timer time;
    private InetAddress ipAddressRec;
    private int portRec;
    private DatagramSocket clientSocket;
    private byte[] sendData;
    private boolean login;
    private String name;
    private int gamer;
    private Records result;
    public Menu(String ipRec, int portRec, DatagramSocket s, Records result, boolean login, String name, int gamer) {
        try {
            addKeyListener(this);
            ipAddressRec = InetAddress.getByName(ipRec);
            this.portRec = portRec;
            clientSocket = s;
            this.result = result;
            this.login = login;
            this.name = name;
            this.gamer = gamer;
            sendData = new byte[1024];
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
            time = new Timer(1, this);
            time.start();
        }
        catch (Exception e) {
            System.out.println("MenuConstraction " + e.getMessage());
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.WHITE);
        g.fillRect(10, gamer1 - 50, 10, 100);
        g.fillRect(780, gamer2 - 50, 10, 100);
        g.fillRect(ballX - 7, ballY - 7, 14, 14);
        g.setFont(font);
        g.drawString(String.valueOf(count1), 360, 30);
        g.drawString(String.valueOf(count2), 420, 30);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    public void set(int bx, int by, int y1, int y2, int c1, int c2) {
        ballX = bx;
        ballY = by;
        gamer1 = y1;
        gamer2 = y2;
        count1 = c1;
        count2 = c2;
        repaint();
        validate();
        if(login) {
            if(gamer == 1) {
                if (count1 != count1last) {
                    result.setCount(name);
                    result.setGames(name);
                    count1last = count1;
                }
                if(count2 != count2last){
                    result.setGames(name);
                    count2last = count2;
                }
            }
            if(gamer == 2) {
                if (count1 != count1last) {
                    result.setGames(name);
                    count1last = count1;
                }
                if(count2 != count2last){
                    result.setCount(name);
                    result.setGames(name);
                    count2last = count2;
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                String s = "2";
                sendData = s.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddressRec, portRec);
                clientSocket.send(sendPacket);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                String s = "1";
                sendData = s.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddressRec, portRec);
                clientSocket.send(sendPacket);
            }
        } catch (Exception x) {
            System.out.println("keyPressed " + x.getMessage());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String s = "3";
                sendData = s.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddressRec, portRec);
                clientSocket.send(sendPacket);
            }
        } catch (Exception x) {
            System.out.println("keyReleased " + x.getMessage());
        }
    }
    public void setEnd() {
        try {
            String s = "0";
            sendData = s.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddressRec, portRec);
            clientSocket.send(sendPacket);
            clientSocket.close();
        }
        catch (Exception x) {
            System.out.println("setEnd " + x.getMessage());
        }
    }
}
