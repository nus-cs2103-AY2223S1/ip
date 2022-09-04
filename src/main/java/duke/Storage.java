package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deals with saving Duke tasks as well as loading them from a file.
 */
public class Storage {
    private final File SAVE_FOLDER;
    private final File SAVE_FILE;

    public Storage(String savePath, String saveName) {
        this.SAVE_FOLDER = new File(savePath);
        this.SAVE_FILE = new File(savePath + saveName);
    }

    /**
     * Saves a list of tasks into the save location.
     *
     * @param tasks A TaskList of tasks to be saved.
     */
    public void saveFile(TaskList tasks) {
        try {
            createSaveFolderIfAbsent();
            writeToSaveFile(tasks);
        } catch (IOException e) {
            final String ERROR_MSG = "An error occurred while attempting to save the file";
            System.out.println(ERROR_MSG);
        }
    }

    private void createSaveFolderIfAbsent() {
        if (!SAVE_FOLDER.exists()) {
            SAVE_FOLDER.mkdir();
        }
    }

    private void writeToSaveFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(SAVE_FILE);
        for (Task t : tasks.getTasks()) {
            String saveTask = t.parseToSaveData();
            fw.write(saveTask + "\n");
        }
        fw.close();
    }

    /**
     * Loads a list of tasks from the save location.
     *
     * @param tasks The destination for tasks to be loaded to.
     */
    public void loadFile(TaskList tasks) {
        try {
            if (SAVE_FILE.exists()) {
                Scanner s = new Scanner(SAVE_FILE);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    loadTaskLineTo(tasks, line);
                }
                s.close();
            }
        } catch (FileNotFoundException e) {
            final String ERROR_MSG = "The duke scanner is unable to load the information in the file.";
            System.out.println(ERROR_MSG);
        }
    }

    /**
     * Load one task into a TaskList.
     *
     * @param tasks The destination for the task to be loaded to.
     * @param line The task to be loaded which is stored in a String format.
     */
    private void loadTaskLineTo(TaskList tasks, String line) {
        String[] taskInfo = parseSavedTask(line);
        addTaskTo(tasks, taskInfo);
    }

    private String[] parseSavedTask(String line) {
        // There are at most 7 parameters in a saved task, including status.
        final int PARSED_ARRAY_SIZE = 7;
        String[] temp = line.split("\\|");
        String[] info = Arrays.copyOf(temp, PARSED_ARRAY_SIZE);
        return info;
    }

    private void addTaskTo(TaskList tasks, String[] taskInfo) {
        String taskType = taskInfo[0];
        int status = Integer.parseInt(taskInfo[1]);
        String description = taskInfo[2];
        String date1 = taskInfo[3];
        String time1 = taskInfo[4];
        String date2 = taskInfo[5];
        String time2 = taskInfo[6];
        switch (taskType) {
        case "T":
            tasks.addTask(new Todo(description, status));
            break;
        case "D":
            tasks.addTask(new Deadline(description, status, date1, time1));
            break;
        case "E":
            tasks.addTask(new Event(description, status, date1, time1, date2, time2));
            break;
        default:
            System.out.println("Unknown task type");
        }
    }
}
