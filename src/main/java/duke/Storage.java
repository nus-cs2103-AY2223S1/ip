package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Storage {

    private static final String FILE_NAME = "data.txt";

    public static List<Task> loadData() {
        try {
            FileInputStream fin = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Task>();
    }

    public static void saveData(List<Task> dukeTasks) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dukeTasks);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
