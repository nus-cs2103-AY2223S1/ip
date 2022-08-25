package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();
    private int count = 0;

    public Storage() {

    };

    public void writeToFile(){
        try {
            new FileWriter("data/duke.txt", false).close();
            for (int i = 0; i < list.size(); i++) {
                FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
                fw.write(list.get(i).getTask());
                fw.write(System.lineSeparator());
                fw.close();
            }
        } catch(IOException e) {
            System.out.println("File not found");
        }
    }
    public ArrayList<Task> getList() {
        return this.list;
    }



}
