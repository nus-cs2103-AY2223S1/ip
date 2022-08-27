package Storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import DataStruct.TaskList;
import DaveExceptions.DaveException;
import DaveExceptions.DaveInitFileException;

public class SaveHandler {

    private final String SAVE_FILE_DIR = System.getProperty("user.dir") + "/save.txt";

    private final File SAVE_FILE = new File(SAVE_FILE_DIR);

    /**
     * Initalises the save file handler. Ensures that the save file exists by creating it if necessary,
     * and checks that the file is able to be read and written to.
     *
     * @throws DaveException Exception is thrown if program is unable to read and/or write to save file.
     */
    public void init() throws DaveException {
        try {
            SAVE_FILE.createNewFile();
        } catch (IOException e) {
            throw new DaveInitFileException();
        }
        if (!SAVE_FILE.canRead() || !SAVE_FILE.canWrite()) {
            throw new DaveException("Oh no! You do not have permission to read and/or write to your saved files :(");
        }
    }

    /**
     * Saves the given task list to the save file.
     * Adapted from https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
     *
     * @param tasks Tasklist object to be saved to the save file.
     * @throws DaveException Exception is thrown if program is unable to save the task list to the file.
     */
    public void save(TaskList tasks) throws DaveException {
        try{
            FileOutputStream file_output = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(file_output);
            oos.writeObject(tasks);
            file_output.close();
        } catch (IOException e) {
            throw new DaveExceptions.DaveException("Oh no! An error has been encountered while saving!");
        }
    }

    /**
     * Loads a task list from the save file.
     * Adapted from https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
     *
     * @return tasks Tasklist object to read from the save file.
     * @throws DaveException Exception is thrown if program is unable to read the task list from the file.
     */
    public TaskList load() throws DaveException {
        if (SAVE_FILE.length() != 0) {
            try{
                FileInputStream fin= new FileInputStream (SAVE_FILE);
                ObjectInputStream ois = new ObjectInputStream(fin);
                TaskList tasks = (TaskList) ois.readObject();
                fin.close();
                return tasks;
            } catch (IOException |ClassNotFoundException e) {
//            throw new DaveExceptions.DaveException("Oh no! An error has been encountered while loading!");
                throw new DaveException(e.toString());
            }
        } else {
            return new TaskList();
        }
    }
}
