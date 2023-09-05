package model;
import io.RecordsReader;
import io.RecordsWriter;
import java.util.Vector;

public class Records {
    private Vector<User> records;
    public Records(){
        records = new Vector<>();
        load();
    }
    synchronized public void PutUser(User user){
        records.add(user);
        RecordsWriter.saveToTextFile("./records.txt", this);
    }
    synchronized public void load(){
        RecordsReader.loadFromTextFile("./records.txt", this);
    }
    synchronized public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < records.size(); i++){
            str.append(records.get(i).getName() + "\n");
            str.append(records.get(i).getPassword() + "\n");
            str.append(records.get(i).getGames() + " " + records.get(i).getPoints());
            if(i != records.size() - 1){
                str.append("\n");
            }
        }
        return str.toString();
    }
    synchronized public String[][] fillArray() {
        String[][] data = new String[records.size()][3];
        for(int i = 0; i < records.size(); i++){
            data[i][0] = records.get(i).getName();
            data[i][1] = String.valueOf(records.get(i).getPoints());
            data[i][2] = String.valueOf(records.get(i).getGames());
        }
        return data;
    }
    synchronized public boolean haveId(String name, String password){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getName().equals(name) && records.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    synchronized public boolean haveName(String name){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    synchronized public void setCount(String name){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getName().equals(name)){
                records.get(i).setPoints();
                RecordsWriter.saveToTextFile("./records.txt", this);
            }
        }
    }
    synchronized public void setGames(String name){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getName().equals(name)){
                records.get(i).setGames();
                RecordsWriter.saveToTextFile("./records.txt", this);
            }
        }
    }
}
