package io;
import model.Records;
import java.io.FileWriter;

public class RecordsWriter {
    static public void saveToTextFile(String filename, Records records) {
        try {
            FileWriter file = new FileWriter(filename);
            file.write(records.toString());
            file.close();
        }
        catch(Exception ex){
            System.out.println("RecordsWriter " + ex.getMessage());
        }
    }
}
