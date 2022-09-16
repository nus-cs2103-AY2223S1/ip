package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;

/**
 * Storage class for storing and retrieving tasks from local file.
 */
public class Storage {
    private File dataFile;

    /**
     * Constructor for Storage class.
     *
     * @param filePath Path to local file to store tasks.
     */
    public Storage(String filePath) {
        try {
            assert filePath != null;
            dataFile = new File(filePath);
            if (dataFile.getParent() != null) {
                File parentDir = new File(dataFile.getParent());
                parentDir.mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Something when wrong when creating your file :( \nError: " + e.getMessage());
        }
    }

    /**
     * Reads existing tasks from dataFile and adds them to tasks.
     */
    public void loadTasks(TaskList taskList) {
        try {
            assert dataFile != null;
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.pushTask(Task.createTask(line));
            }
            scanner.close();
        } catch (IOException | DukeException e) {
            System.out.println("Something when wrong when reading your file :( \nError: " + e.getMessage());
        }
    }

    /**
     * Saves tasks to dataFile.
     */
    public void saveTasks(TaskList taskList) {
        try {
            assert dataFile != null;
            FileWriter writer = new FileWriter(dataFile);
            writer.write(taskList.getFileText());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something when wrong when writing your file :( \nError: " + e.getMessage());
        }
    }
}
