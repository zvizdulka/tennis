package model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game  implements ActionListener{
    private int gamer1, gamer2, ballX, ballY;
    private int stepX = 6, stepY = 6;
    private int speed = 48;
    private int count1 = 0;
    private int count2 = 0;
    private boolean istime = true; // in game
    private Timer time;
    public Game(){
        gamer1 = 300;
        gamer2 = 300;
        ballX = 400;
        ballY = 300;
        time = new Timer(20, this);
    }
    synchronized public int getGamer1(){
        return gamer1;
    }
    synchronized public int getGamer2(){
        return gamer2;
    }
    public int getBallX(){
        return ballX;
    }
    public int getBallY(){
        return ballY;
    }
    synchronized public void Down1(){
        if(gamer1 < 510){
            gamer1+=speed;
        }
        if(gamer1 > 510){
            gamer1 = 520;
        }
    }
    synchronized public void Down2(){
        if (gamer2 < 510) {
            gamer2 += speed;
        }
        if (gamer2 > 510) {
            gamer2 = 520;
        }
    }
    synchronized public void Up1(){
        if(gamer1 > 70){
            gamer1-=speed;
        }
        if(gamer1 < 70){
            gamer1 = 55;
        }
    }
    synchronized public void Up2(){
        if (gamer2 > 70) {
            gamer2 -= speed;
        }
        if (gamer2 < 70) {
            gamer2 = 55;
        }
    }
    public void Ball(){
        if(!istime){
            istime = true;
        }
        ballX+=stepX;
        ballY+=stepY;
        if(ballY <= 0 || ballY >= 570){
            stepY= -stepY;
        }
        if((ballX >= 16 && ballX <= 22)  && (ballY <= gamer1 + 50 && ballY >= gamer1 - 50)){
            stepX = -stepX;
        }
        if((ballX >= 778 && ballX <= 782) && (ballY <= gamer2 + 50 && ballY >= gamer2 - 50)){
            stepX = -stepX;
        }
        if(ballX < -20){
            count2++;
            gamer1 = 300;
            gamer2 = 300;
            ballX = 400;
            ballY = 300;
            stepX = 6;
            stepY = 6;
            istime = false;
        }
        if(ballX > 820){
            count1++;
            gamer1 = 300;
            gamer2 = 300;
            ballX = 400;
            ballY = 300;
            stepX = -6;
            stepY = -6;
            istime = false;
        }
    }
    public boolean getTime(){
        return istime;
    }
    public int getCount1(){
        return count1;
    }
    public int getCount2(){
        return count2;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Ball();
        if(!getTime()){
            time.stop();
        }
    }
    public void setTime(){
        time.start();
    }
}
