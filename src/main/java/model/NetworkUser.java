package model;

public class NetworkUser {
    private int port;
    private String ip;
    public NetworkUser(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
    public String getIp(){
        return ip;
    }
    public int getPort(){
        return port;
    }
    public boolean isThisUser(NetworkUser u){
        return ((u.getIp().equals(ip)) && (u.getPort() == port));
    }
    public String toString(){
        String s = new String(ip + " " + port);
        return s;
    }
}
