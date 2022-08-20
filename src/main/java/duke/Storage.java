package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;


public class Storage {

    private static final String FILE_NAME = "data.txt";

    public static ArrayList<Task> loadData() {
        try {
            FileInputStream fin = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fin);
            @SuppressWarnings("unchecked") ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
            return tasks;
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
