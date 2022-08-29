package duke.storage;

import duke.listobjects.ListObject;
import duke.tasklist.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Path;

public class Storage {
    private static TaskList tasksStored;
    private static File listFile;

    public Storage(String filePath){
        this.tasksStored = readFromFile(filePath);
        File listFile = new File(filePath);
    }

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