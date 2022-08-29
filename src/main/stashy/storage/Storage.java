package main.stashy.storage;

import main.stashy.data.exception.StashyException;
import main.stashy.data.task.Task;
import main.stashy.data.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to store, load, and save data into or from
 * a specific file as compact lists.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor method.
     *
     * @param filePath The file path specified
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes the input file into a list of array of strings
     * for the parser to handle afterwards.
     *
     * @return List of array of strings from the lines in the file path specified
     * @throws StashyException If there is an (I/O) issue, e.g. file not found
     */
    public ArrayList<String> load() throws StashyException {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            ArrayList<String> loadResult = new ArrayList<String>();
            while (s.hasNext()) {
                loadResult.add(s.nextLine());
            }
            return loadResult;
        } catch (Exception e) {
            throw new StashyException(e.getMessage());
        }
    }

    /**
     * Creates the file if one doesn't exist.
     * No effect if file exists in the first place.
     * <p>The mkdirs() method can be used with a different
     * context since it doesn't create a file but a
     * directory instead.</p>
     *
     * @throws StashyException If any (I/O) issue happens
     */
    public void create() throws StashyException {
        try {
            String[] pathList = this.filePath.split("/");
            String currentPath = "";
            for (int i = 0; i < pathList.length - 1; i++) {
                currentPath += pathList[i];
                File directory = new File(currentPath);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                currentPath += "/";
            }
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception e) {
            throw new StashyException(e.getMessage());
        }
    }

    /**
     * Writes the current task list into the storage again.
     *
     * @param taskList The list of tasks
     * @throws StashyException If any (I/O) issue happens
     */
    public void writeTaskListToFile(TaskList taskList)
            throws StashyException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.toArrayList()) {
                fw.write(task + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new StashyException(e.getMessage());
        }
    }
}