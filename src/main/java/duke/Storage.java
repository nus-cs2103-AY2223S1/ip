package duke;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;



public class Storage {
    public static void writeToFile(ArrayList<Task> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null) {
                try {
                    FileWriter fw = new FileWriter("data/duke.txt",true);
                    fw.write(array.get(i).toString() + System.lineSeparator());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static boolean fileExists() {
        File f = new File("data/duke.txt");
        return f.exists();
    }

    public static void clearFile() {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter("data/duke.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
