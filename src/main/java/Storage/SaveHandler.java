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

    protected final String CURR_DIR = System.getProperty("user.dir");

    private final String SAVE_FILE_DIR = CURR_DIR + "/save.txt";

    private final File SAVE_FILE = new File(SAVE_FILE_DIR);

    protected void initFile(File file) throws DaveException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DaveInitFileException();
        }
        if (!file.canRead() || !file.canWrite()) {
            throw new DaveException("Oh no! You do not have permission to read and/or write to your saved files :(");
        }
    }

    protected void write(TaskList tasks, File file) throws DaveException {
        try{
            FileOutputStream file_output = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(file_output);
            oos.writeObject(tasks);
            file_output.close();
        } catch (IOException e) {
            throw new DaveExceptions.DaveException("Oh no! An error has been encountered while saving!");
        }
    }

    protected TaskList read(File file) throws DaveException {
        if (file.length() != 0) {
            try{
                FileInputStream fin= new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                TaskList tasks = (TaskList) ois.readObject();
                fin.close();
                return tasks;
            } catch (IOException | ClassNotFoundException e) {
//                throw new DaveExceptions.DaveException("Oh no! An error has been encountered while loading!");
                throw new DaveException(e.toString());
            }
        } else {
            return new TaskList();
        }
    }

    /**
     * Initialises the save file handler. Ensures that the save file exists by creating it if necessary,
     * and checks that the file is able to be read and written to.
     *
     * @throws DaveException Exception is thrown if program is unable to read and/or write to save file.
     */
    public void init() throws DaveException {
        initFile(SAVE_FILE);
    }

    /**
     * Saves the given task list to the save file.
     * Adapted from https://stackoverflow.com/questions/16111496/
     *     java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
     *
     * @param tasks Tasklist object to be saved to the save file.
     * @throws DaveException Exception is thrown if program is unable to save the task list to the file.
     */
    public void save(TaskList tasks) throws DaveException {
        write(tasks, SAVE_FILE);
    }

    /**
     * Loads a task list from the save file.
     * Adapted from https://stackoverflow.com/questions/16111496/
     *     java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
     *
     * @return tasks Tasklist object to read from the save file.
     * @throws DaveException Exception is thrown if program is unable to read the task list from the file.
     */
    public TaskList load() throws DaveException {
        return read(SAVE_FILE);
    }
}
