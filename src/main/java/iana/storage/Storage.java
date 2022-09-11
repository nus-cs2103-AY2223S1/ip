package iana.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import iana.exception.IanaException;
import iana.tasks.TaskList;

/**
 * Stores tasks and their relevant information.
 */
public class Storage {
    private static final String FILE_STRING = "data/DataStorage.txt";

    public static void initialise() throws IanaException {
        try {
            Path filePath = Paths.get(Storage.FILE_STRING);
            if (!Files.exists(filePath)) {
                File file = new File(Storage.FILE_STRING);
                file.getParentFile().mkdir();
                file.createNewFile();
                TaskList tasks = new TaskList();
                Storage.store(tasks);
            }
        } catch (IOException e) {
            throw new IanaException("Oh no, the storage file is corrupted!");
        }
    }

    /**
     * Reads tasks' data from storage file and returns the task list.
     * 
     * @return task list of stored data.
     * @throws IanaException if file or folder does not exist or file is corrupted.
     */
    public static TaskList load() throws IanaException {
        TaskList taskList = new TaskList();

        try {
            FileInputStream fileIn = new FileInputStream(Storage.FILE_STRING);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            taskList = (TaskList) in.readObject();
            in.close();
            fileIn.close();
            if (taskList == null) {
                return new TaskList();
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new IanaException("File not found!xx");
        } catch (IOException e) {
            throw new IanaException("Oh no, the storage file is corrupted! :(");
        } catch (ClassNotFoundException e) {
            throw new IanaException("Class not found");
        }
    } 

    /**
     * Saves task list data into storage file.
     * 
     * @param taskList task list of tasks to be stored.
     * 
     * @throws IanaException if file or folder does not exist.
     */
    public static void store(TaskList taskList) throws IanaException {
        try {
            FileOutputStream fileOut = new FileOutputStream(Storage.FILE_STRING);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(taskList);
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
            throw new IanaException("Naur, I can't seem to store the tasks!");
        }
    }
}
