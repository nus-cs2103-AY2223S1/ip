package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import duke.task.Task;

/**
 * Encapsulates a Storage
 */
public class Storage {

    private static final String FILE_NAME = "data.txt";

    /**
     * Returns an array list of tasks stored from previous runs
     *
     * @return List of Tasks
     * */
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

    /**
     * Saves the current tasks stored in Duke into a text file
     * */
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
