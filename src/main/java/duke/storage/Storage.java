package duke.storage;

import duke.listobjects.ListObject;
import duke.tasklist.TaskList;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represents Storage class responsible for editing and saving user's task list information
 * in a file and reading from saved file
 */
public class Storage {
    private static TaskList tasksStored;
    private static File listFile;

    /**
     * Constructs a Storage object which reads from and writes to a file of given file path
     *
     * @param filePath String representing file path
     */

    public Storage(String filePath) {
        try {
            tasksStored = readFromFile(filePath);
            File listFile = new File(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads from file with given file path containing saved user tasks, if one exists, or creates such a file
     *
     * @param path String representing file path
     * @return TaskList saved in the file (if it exists) or a new one otherwise
     */

    public static TaskList readFromFile(String path) {
        TaskList inList = new TaskList();
        try {
            File listFile = new File(path);
            listFile.createNewFile();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(listFile));
            @SuppressWarnings("unchecked")
            ArrayList<ListObject> allIn = (ArrayList<ListObject>) in.readObject();
            inList.setTasks(allIn);
            in.close();
        } catch (EOFException e) {
            System.out.println("Nothing here yet...");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return inList;
        }
    }


    /**
     * Updates the file with given path to store the new list of tasks the user wants to save
     *
     * @param path String representing file path
     * @param lst  TaskList with a list of tasks the user wishes to save
     */

    public static void makeListFile(String path, TaskList lst) {
        try {
            File listFile = new File(path);
            ArrayList<ListObject> listOfItems = lst.storeAllTasks();
            //adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(listFile));
            out.writeObject(listOfItems);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
