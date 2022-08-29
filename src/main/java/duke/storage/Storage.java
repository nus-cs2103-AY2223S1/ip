package duke.storage;

import duke.tasklist.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Path;

public class Storage {
    private static TaskList tasksStored;

    public Storage(String filePath){
        this.tasksStored = readFromFile(filePath);
    }

    public static TaskList readFromFile(String path){
        TaskList inList = new TaskList();
        try {
            File listFile = new File(path);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(listFile));
            inList = (TaskList) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            return inList;
        }
    }

    public static void makeListFile(String path, TaskList lst){
        try {
            File listFile = new File(path);
            //adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(listFile));
            out.writeObject(lst);
            out.close();
        }
        catch(IOException e){
            System.out.println(e.toString());
        }

    }


}