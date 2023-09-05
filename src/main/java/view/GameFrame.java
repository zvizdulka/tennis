package view;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.net.DatagramSocket;

public class GameFrame extends JFrame {
    private Container c;
    private Menu game;
    private JPanel panel;
    public static Records result;
    private boolean login;
    private String user;

    private ImageIcon homeImg;
    private JButton homeButton;
    private JButton homeInGameButton;
    private ImageIcon newGameImg;
    private JButton newGameButton;
    private ImageIcon joinImg;
    private JButton joinButton;
    private ImageIcon recordsImg;
    private JButton recordsButton;
    private ImageIcon loginImg;
    private JButton loginButton;
    private ImageIcon logoutImg;
    private JButton logoutButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private Font font;
    private ImageIcon loginFieldImg;
    private JButton loginFieldButton;
    private JButton homeFieldButton;
    private ImageIcon registerImg;
    private JButton registerButton;
    public GameFrame(){
        super("Теннис");
        try {
            setSize(800, 600);
            Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension framesize = getSize();
            int x = (int) screensize.getWidth() / 2 - (int) framesize.getWidth() / 2;
            int y = (int) screensize.getHeight() / 2 - (int) framesize.getHeight() / 2;
            setLocation(x, y);
            login = false;

            InputStream file = GameFrame.class.getResourceAsStream("/res/newgame.png");
            newGameImg = new ImageIcon(ImageIO.read(file));
            file.close();
            newGameButton = new JButton(newGameImg);
            newGameButton.addActionListener(new newGameListener());
            newGameButton.setBorderPainted(false); // прорисовка рамки
            newGameButton.setFocusPainted(false);// прорисовка контура
            newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            file = GameFrame.class.getResourceAsStream("/res/join.png");
            joinImg = new ImageIcon(ImageIO.read(file));
            file.close();
            joinButton = new JButton(joinImg);
            joinButton.addActionListener(new joinListener());
            joinButton.setBorderPainted(false);
            joinButton.setFocusPainted(false);
            joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            file = GameFrame.class.getResourceAsStream("/res/records.png");
            recordsImg = new ImageIcon(ImageIO.read(file));
            file.close();
            recordsButton = new JButton(recordsImg);
            recordsButton.addActionListener(new recordsListener());
            recordsButton.setBorderPainted(false);
            recordsButton.setFocusPainted(false);
            recordsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            file = GameFrame.class.getResourceAsStream("/res/login.png");
            loginImg = new ImageIcon(ImageIO.read(file));
            file.close();
            loginButton = new JButton(loginImg);
            loginButton.addActionListener(new loginListener());
            loginButton.setBorderPainted(false);
            loginButton.setFocusPainted(false);
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            file = GameFrame.class.getResourceAsStream("/res/logout.png");
            logoutImg = new ImageIcon(ImageIO.read(file));
            file.close();
            logoutButton = new JButton(logoutImg);
            logoutButton.addActionListener(new logoutListener());
            logoutButton.setBorderPainted(false);
            logoutButton.setFocusPainted(false);
            logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            file = GameFrame.class.getResourceAsStream("/res/home.png");
            homeImg = new ImageIcon(ImageIO.read(file));
            file.close();
            homeButton = new JButton(homeImg);
            homeButton.setBorderPainted(false);
            homeButton.setFocusPainted(false);
            homeButton.addActionListener(new GameFrame.homeListener());
            homeInGameButton = new JButton(homeImg);
            homeInGameButton.setBorderPainted(false);
            homeInGameButton.setFocusPainted(false);
            homeInGameButton.addActionListener(new GameFrame.homeListenerinGame());
            file = GameFrame.class.getResourceAsStream("/res/register.png");
            registerImg = new ImageIcon(ImageIO.read(file));
            file.close();
            registerButton = new JButton(registerImg);
            registerButton.addActionListener(new registerFieldListener());
            registerButton.setBorderPainted(false);
            registerButton.setFocusPainted(false);
            registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            homeFieldButton = new JButton(homeImg);
            homeFieldButton.setBorderPainted(false);
            homeFieldButton.setFocusPainted(false);
            homeFieldButton.addActionListener(new GameFrame.homeListener());
            homeFieldButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            file = GameFrame.class.getResourceAsStream("/res/login.png");
            loginFieldImg = new ImageIcon(ImageIO.read(file));
            file.close();
            loginFieldButton = new JButton(loginFieldImg);
            loginFieldButton.addActionListener(new loginFieldListener());
            loginFieldButton.setBorderPainted(false);
            loginFieldButton.setFocusPainted(false);
            loginFieldButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
            loginField = new JTextField("NAME", 5);
            loginField.setFont(font);
            loginField.setMaximumSize(new Dimension(400, 50));
            loginField.setHorizontalAlignment(JTextField.CENTER);
            loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
            passwordField = new JPasswordField("PASSWORD", 5);
            passwordField.setFont(font);
            passwordField.setEchoChar('*');
            passwordField.setMaximumSize(new Dimension(400, 50));
            passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
            passwordField.setHorizontalAlignment(JTextField.CENTER);

            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.BLACK);
            first();
            panel.add(loginButton);
            result = new Records();
            c = getContentPane();
            c.add(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
        catch (Exception e) {
            System.out.println("GameFrame constraction" + e.getMessage());
        }
    }
    public void first(){
        panel.removeAll();
        panel.add(newGameButton);
        panel.add(joinButton);
        panel.add(recordsButton);
        if(!login) {
            panel.add(loginButton);
        }
        else{
            panel.add(logoutButton);
        }
        repaint();
        validate();
    }
    public void second(String ipRec, int portRec, DatagramSocket s, int gamer){
        game = new Menu(ipRec, portRec, s, result, login, user, gamer);
        game.setLayout(new BorderLayout());
        game.add(homeInGameButton, BorderLayout.SOUTH);
        c.add(game);
        panel.setVisible(false);
        panel.setFocusable(false);
        game.setVisible(true);
        game.setFocusable(true);
        game.requestFocusInWindow();
        repaint();
        validate();
    }
    public void pageLogin(boolean remove){
        if(remove) {
            panel.removeAll();
        }
        panel.add(homeFieldButton);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(loginFieldButton);
        panel.add(registerButton);
        repaint();
        validate();
    }
    class newGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            try {
                ClientNewGame client = new ClientNewGame("127.0.0.1", 8080);
                second(client.getIpServer(), client.getPortServer(), client.getClientSocket(), 1);
                ReceiveThread rec = new ReceiveThread(client.getClientSocket(), game);
                rec.start();
            }
            catch (Exception e) {
                System.out.println("newGameListener " + e.getMessage());
            }
        }
    }
    class joinListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            try {
                ClientJoin client = new ClientJoin("127.0.0.1", 8080);
                if (client.inGame()) {
                    second(client.getIpServer(), client.getPortServer(), client.getClientSocket(), 2);
                    ReceiveThread rec = new ReceiveThread(client.getClientSocket(),game);
                    rec.start();
                }
            }
            catch (Exception e) {
                System.out.println("joinListener " + e.getMessage());
            }
        }
    }
    class recordsListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            RecordsFrame window = new RecordsFrame();
        }
    }
    class homeListenerinGame implements ActionListener {
        public void actionPerformed(ActionEvent event){
            game.setEnd();
            game.setVisible(false);
            panel.setVisible(true);
            first();
        }
    }
    class homeListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            first();
        }
    }
    class loginListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            pageLogin(true);
        }
    }
    class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            login = false;
            user = null;
            first();
        }
    }
    class loginFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String name = loginField.getText();
            String password = String.valueOf(passwordField.getPassword());
            if(result.haveId(name, password)){
                login = true;
                user = new String(name);
                first();
            }
            else {
                JLabel error = new JLabel("LOG IN ERROR! NAME OR PASSWONT ISNT CORRECT");
                error.setFont(font);
                error.setForeground(Color.WHITE);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.removeAll();
                panel.add(error);
                pageLogin(false);
            }
        }
    }
    class registerFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String name = loginField.getText();
            String password = String.valueOf(passwordField.getPassword());
            if(result.haveName(name)){
                JLabel error = new JLabel("REGISTER ERROR! NAME IS ALREADY TAKEN");
                error.setFont(font);
                error.setForeground(Color.WHITE);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.removeAll();
                panel.add(error);
                pageLogin(false);
            }
            else{
                result.PutUser(new User(name, password, 0, 0));
                login = true;
                user = new String(name);
                first();
            }
        }
    }
}
