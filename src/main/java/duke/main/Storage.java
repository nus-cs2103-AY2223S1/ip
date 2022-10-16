package duke.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Class dealing with loading tasks from the save file and saving tasks into the file.
 */
public class Storage {
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final Path DATA_FOLDER_PATH = Paths.get(PROJECT_ROOT, "data");
    private static boolean directoryExists = java.nio.file.Files.exists(DATA_FOLDER_PATH);
    private static final Path SAVE_FILE_PATH = DATA_FOLDER_PATH.resolve("duke.txt");
    private static boolean fileExists = Files.exists(SAVE_FILE_PATH);

    /**
     * Saves input task list to the save file.
     * @param taskList TaskList to be saved.
     */
    public static void saveTaskList(TaskList taskList) throws DukeException {
        // Create directory if it does not exist
        if (!directoryExists) {
            try {
                Files.createDirectory(DATA_FOLDER_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save task list into save file
        try {
            if (fileExists) {
                // Overwrite the current save file
                clearSaveFile();
                for (int i = 1; i <= taskList.getSize(); i++) {
                    Task task = taskList.getTask(Integer.toString(i));
                    String reformattedTask = task.changeFormat();
                    Files.write(SAVE_FILE_PATH, (reformattedTask + System.lineSeparator()).getBytes(),
                            StandardOpenOption.APPEND);
                }
            } else {
                // Create new file and save
                Files.createFile(SAVE_FILE_PATH);
                for (int i = 1; i <= taskList.getSize(); i++) {
                    Task task = taskList.getTask(Integer.toString(i));
                    String reformattedTask = task.changeFormat();
                    Files.write(SAVE_FILE_PATH, (reformattedTask + System.lineSeparator()).getBytes(),
                            StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Clears the data in the save file.
     */
    public static void clearSaveFile() {
        try {
            Files.newBufferedWriter(SAVE_FILE_PATH, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads saved tasks in the save file into the TaskList.
     * @throws DukeException If there is an error loading the file.
     */
    public static ArrayList<Task> loadTaskList() throws DukeException {
        try {
            ArrayList<Task> tempTaskList = new ArrayList<>();
            String[] linesArr = Files.lines(SAVE_FILE_PATH).toArray(String[]::new);
            for (String l : linesArr) {
                tempTaskList.add(parseString(l));
            }
            return tempTaskList;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Hmm... looks like you don't have an existing save file, let's make one!");
        }

    }
    /**
     * Parses a Task according to the details from the String.
     * @param taskString String containing details of the task.
     * @return Task created with details from the input.
     * @throws DukeException If there is error parsing the output task.
     */
    private static Task parseString(String taskString) throws DukeException {
        // Split the string into an array of properties
        String[] taskProperties = taskString.split(" \\| ", 5);
        try {
            String taskType = taskProperties[0];
            String taskDone = taskProperties[1];
            String taskDescription = taskProperties[2];
            String taskNote = taskProperties[3];
            Task task = null;

            switch (taskType) {
            case "T": {
                task = new Todo(taskDescription);
                break;
            }
            case "E": {
                String taskDateTime = taskProperties[4];
                LocalDateTime dateTime = DateTime.parseDate(taskDateTime);
                task = new Event(taskDescription, dateTime);
                break;
            }
            case "D": {
                String taskDateTime = taskProperties[4];
                LocalDateTime dateTime = DateTime.parseDate(taskDateTime);
                task = new Deadline(taskDescription, dateTime);
                break;
            }
            default: {
                break;
            }
            }

            if (taskDone.equals("X") && task != null) {
                task.mark();
            }
            if (!taskNote.equals("-") && task != null) {
                task.addNote(taskNote);
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Error in saved file!");
        }
    }
}
