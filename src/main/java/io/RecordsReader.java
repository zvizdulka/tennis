package io;
import model.Records;
import model.User;
import java.io.FileReader;
import java.util.Scanner;

public class RecordsReader {
    public static void loadFromTextFile(String filename, Records records) {
        try {
            FileReader file = new FileReader(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String name = sc.nextLine();
                String password = sc.nextLine();
                int games = sc.nextInt();
                int points = sc.nextInt();
                if(sc.hasNext()) {
                    sc.nextLine();
                }
                records.PutUser(new User(name, password, games, points));
            }
            file.close();
        }
        catch(Exception ex){
            System.out.println("RecordsReader " + ex.getMessage());
        }
    }
}
