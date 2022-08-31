package duke.storage;

import duke.listobjects.ListObject;
import duke.tasklist.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Path;

/**
 * Represents Storage class responsible for editing and saving user's task list information in a file and reading from saved file
 */
public class Storage {
    private static TaskList tasksStored;
    private static File listFile;

    /**
     * Constructs a Storage object which reads from and writes to a file of given file path
     * @param filePath String representing file path
     */
    public Storage(String filePath){
        this.tasksStored = readFromFile(filePath);
        File listFile = new File(filePath);
    }

    /**
     * Reads from file with given file path containing saved user tasks, if one exists, or creates such a file
     * @param path String representing file path
     * @return TaskList saved in the file (if it exists) or a new one otherwise
     */
    public static TaskList readFromFile(String path){
        TaskList inList = new TaskList();
        try {
            File listFile = new File(path);
            listFile.createNewFile();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(listFile));
            ArrayList<ListObject> allTasks = (ArrayList<ListObject>) in.readObject();
            inList.setTasks(allTasks);
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            return inList;
        }
    }

    /**
     * Updates the file with given path to store the new list of tasks the user wants to save
     * @param path String representing file path
     * @param lst TaskList with a list of tasks the user wishes to save
     */
    public static void makeListFile(String path, TaskList lst){
        try {
            File listFile = new File(path);
            ArrayList<ListObject> listOfItems = lst.storeAllTasks();
            //adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(listFile));
            out.writeObject(listOfItems);
            out.close();
        }
        catch(IOException e){
            System.out.println(e.toString());
        }

    }


}