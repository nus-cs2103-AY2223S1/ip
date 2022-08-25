package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;



public class Storage {
    public void writeToFile(ArrayList<Task> array, int counter) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt",true);
            fw.write(array.get(counter).toString() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fileExists() {
        File f = new File("data/duke.txt");
        return f.exists();
    }
}
