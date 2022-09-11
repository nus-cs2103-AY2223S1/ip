package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.javafx.css.PseudoClassState;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /** Get the folder path to data which is working directory + /data. */
    private static final Path FOLDER_PATH = Paths.get(System.getProperty("user.dir") + "/data");

    /** Get the file path which is Folder path + /duke.txt. */
    private static final Path FILE_PATH = Paths.get(FOLDER_PATH + "/duke.txt");

    private static final int TASK_TYPE_POSITION = 1;
    private static final int CHARS_BEFORE_DESCRIPTION = 7;
    private static final char TODO_SYMBOL = 'T';
    private static final char EVENT_SYMBOL = 'E';
    private static final char DEADLINE_SYMBOL = 'D';

    /**
     * Handles loading of data from file
     *
     * @return  Returns the tasklist that consist of data loaded from the file
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     *     This class is the general class of exceptions produced by failed or interrupted
     *     I/O operations.
     */
    public static TaskList load() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        boolean fileExists = java.nio.file.Files.exists(FILE_PATH);
        TaskList taskList = new TaskList();
        if (!directoryExists) {
            Files.createDirectories(FOLDER_PATH);
        }
        if (!fileExists) {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            fw.close();
        }
        File f = new File(FILE_PATH.toString());
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            Parser.parseStorageData(taskList, line);
        }
        return taskList;
    }

    /**
     * Saves the tasks into file.
     *
     * @param taskListToAdd The tasklist that contains all tasks.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     *     This class is the general class of exceptions produced by failed or interrupted
     *     I/O operations.
     */
    public static void save(ArrayList<Task> taskListToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH.toString());
        String fullText = "";
        int listLength = taskListToAdd.size();
        for (int i = 0; i < listLength; i++) {
            fullText = fullText + taskListToAdd.get(i).getDescription() + System.lineSeparator();
        }
        fw.write(fullText);
        fw.close();
    }
}
