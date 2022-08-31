package duke.storage;

import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /** Get the folder path to data which is working directory + /data. */
    private final static Path FOLDER_PATH = Paths.get(System.getProperty("user.dir") + "/data");

    /** Get the file path which is Folder path + /duke.txt. */
    private final static Path FILE_PATH = Paths.get(FOLDER_PATH + "/duke.txt");

    /**
     * Handles loading of data from file
     *
     * @return  Returns the tasklist that consist of data loaded from the file
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * This class is the general class of exceptions produced by failed or interrupted
     * I/O operations.
     */
    public static TaskList load() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        boolean fileExists = java.nio.file.Files.exists(FILE_PATH);
        TaskList taskList = new TaskList();
        if(!directoryExists) {
            Files.createDirectories(FOLDER_PATH);
        }
        if(!fileExists) {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            fw.close();
        }
        File f = new File(FILE_PATH.toString());
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
            char taskStatus = line.charAt(4);
            if (taskType == 'T') {
                String description = line.substring(7);
                taskList.addTaskWithoutPrinting(new ToDo(description, taskStatus));
            } else if (taskType == 'D') {
                String descriptionAndDate = line.substring(7);
                String[] arguments  = descriptionAndDate.split("\\(");
                String description = arguments[0];
                LocalDate date = LocalDate.parse(removeLastChar(arguments[1]));
                taskList.addTaskWithoutPrinting(new Deadline(description, date, taskStatus));
            }else if (taskType == 'E') {
                String descriptionAndDate = line.substring(7);
                String[] arguments  = descriptionAndDate.split("\\(");
                String description = arguments[0];
                LocalDate date = LocalDate.parse(removeLastChar(arguments[1]));
                taskList.addTaskWithoutPrinting(new Event(description, date, taskStatus));
            }
        }
        return taskList;
    }

    /**
     * Saves the tasks into file.
     *
     * @param taskListToAdd The tasklist that contains all tasks.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * This class is the general class of exceptions produced by failed or interrupted
     * I/O operations.
     */
    public static void save(ArrayList<Task> taskListToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH.toString());
        String fullText = "";
        int ListLength = taskListToAdd.size();
        for (int i = 0; i < ListLength; i++) {
            fullText = fullText + taskListToAdd.get(i).getDescription() + System.lineSeparator();
        }
        fw.write(fullText);
        fw.close();
    }

    /**
     * Removes the last char in a string.
     *
     * @param s The string to remove last char.
     * @return The string without last char.
     */
    public static String removeLastChar(String s) {
        s = s.substring(0, s.length()-1);
        return s;
    }
}
