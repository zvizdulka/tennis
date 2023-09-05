package model;

public class User {
    private String name;
    private String password;
    private int countGames;
    private int countPoints;
    public User(String name, String password, int games, int points){
        this.name = name;
        this.password = password;
        countGames = games;
        countPoints = points;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getGames(){
        return countGames;
    }
    public int getPoints(){
        return countPoints;
    }
    public void setGames(){
        countGames++;
    }
    public void setPoints(){countPoints++;}
}
