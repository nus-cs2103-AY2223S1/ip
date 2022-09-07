package carbon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import carbon.error.CorruptedSaveFileException;

/**
 * Stores data on tasks and acts as interface for storage.
 * Used to save data and load data from a default filepath.
 */
public class Storage {
    /** Default filepath for storing data on tasks */
    private static final String SAVEFILENAME = "tasks.txt";
    private static final String UNDOFILENAME = "undo.txt";
    private static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String STORE_DIR = Storage.WORKING_DIR + "/store/";
    private static final String SAVEFILEPATH = Storage.STORE_DIR + Storage.SAVEFILENAME;
    private static final String UNDOFILEPATH = Storage.STORE_DIR + Storage.UNDOFILENAME;

    /**
     * Constructs an instance of Storage class.
     * Main constructor method.
     *
     * @return Storage object.
     */
    public Storage() {}

    private TaskList loadFile(String filepath) throws FileNotFoundException, CorruptedSaveFileException {
        TaskList taskList = new TaskList();
        File saveFile = new File(filepath);
        if (!saveFile.isFile()) {
            throw new FileNotFoundException("File does not exist");
        }

        Scanner saveFileScanner = new Scanner(saveFile);
        while (saveFileScanner.hasNextLine()) {
            String data = saveFileScanner.nextLine();
            taskList.loadTask(data);
        }
        saveFileScanner.close();

        return taskList;
    }

    private void writeTasks(TaskList taskList, String filepath) {
        File saveFile = new File(filepath);
        File saveFileDir = new File(Storage.STORE_DIR);
        try {
            // ensures dir and file exists
            saveFileDir.mkdir();
            saveFile.createNewFile();

            FileWriter writer = new FileWriter(saveFile);
            writer.write(taskList.encodeTasks());
            writer.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    /**
     * Loads the save file and retrieves saved tasks.
     * Uses the default filepath.
     *
     * @return TaskList instance containing the saved tasks.
     */
    public TaskList loadSaveFile() throws FileNotFoundException, CorruptedSaveFileException {
        return this.loadFile(Storage.SAVEFILEPATH);
    }

    /**
     * Saves the tasks in the save file.
     * Uses the default filepath.
     *
     * @param taskList Instance of TaskList containing tasks to be saved.
     */
    public void writeSaveTasks(TaskList taskList) {
        this.writeTasks(taskList, Storage.SAVEFILEPATH);
    }

    /**
     * Loads the undo file and retrieves the previous state of saved tasks.
     * Uses the default filepath.
     *
     * @return TaskList instance containing the previous saved tasks.
     */
    public TaskList loadUndoFile() throws FileNotFoundException, CorruptedSaveFileException {
        return this.loadFile(Storage.UNDOFILEPATH);
    }

    /**
     * Saves the tasks in the undo file.
     * Uses the default filepath.
     *
     * @param taskList Instance of TaskList containing tasks to be saved.
     */
    public void writeUndoTasks(TaskList taskList) {
        this.writeTasks(taskList, Storage.UNDOFILEPATH);
    }
}
