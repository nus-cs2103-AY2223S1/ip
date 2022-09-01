package carbon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Stores data on tasks and acts as interface for storage.
 * Used to save data and load data from a default filepath.
 */
public class Storage {
    /** Default filepath for storing data on tasks */
    private static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String SAVEFILE = Storage.WORKING_DIR + "/../../../store/tasks.txt";
    private static final String SAVEFILE_DIR = Storage.WORKING_DIR + "/../../../store/";

    /**
     * Constructs an instance of Storage class.
     * Main constructor method.
     *
     * @return Storage object.
     */
    public Storage() {}

    /**
     * Loads the savefile and retrieves saved tasks.
     * Uses the default filepath.
     *
     * @return TaskList instance containing the saved tasks.
     */
    public TaskList loadSaveFile() {
        TaskList taskList = new TaskList();

        try {
            File saveFile = new File(Storage.SAVEFILE);
            if (saveFile.isFile()) {
                Scanner saveFileScanner = new Scanner(saveFile);
                while (saveFileScanner.hasNextLine()) {
                    String data = saveFileScanner.nextLine();
                    taskList.loadTask(data);
                }
                saveFileScanner.close();
            }
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }

        return taskList;
    }

    /**
     * Saves the tasks in the save file.
     * Uses the default filepath.
     *
     * @param taskList Instance of TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        File saveFile = new File(Storage.SAVEFILE);
        File saveFileDir = new File(Storage.SAVEFILE_DIR);
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
}
