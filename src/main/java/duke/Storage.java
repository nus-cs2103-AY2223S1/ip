package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * This class encapsulates the logic of the storage of the task list in the user's harddrive.
 */
public class Storage {
    /* Const fields for representation of the various paths. */
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final Path SAVE_LOCATION = Path.of(PROJECT_ROOT, "data");
    private static final String SAVE_FILE_NAME = "Task List.txt";
    private static final Path SAVE_FILE_PATH = (SAVE_LOCATION).resolve(SAVE_FILE_NAME);

    /**
     * Loads the data from the file found in the saved location into the task list.
     *
     * @param tasks task list.
     */
    public static void load(List<Task> tasks) {
        // This is the current directory the system is in.
        boolean isDataFolderPresent = Files.exists(SAVE_LOCATION);
        boolean isSaveFilePresent = Files.exists(SAVE_FILE_PATH);

        // Checks if the data folder exists already, if not, creates one.
        if (!isDataFolderPresent) {
            try {
                Files.createDirectories(SAVE_LOCATION);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Checks if the Save File is present, if not, create one.
        if (isSaveFilePresent) {
            List<String> listContents = new ArrayList<>();
            try {
                listContents = Files.readAllLines(SAVE_FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load contents of file into tasks list.
            for (String s : listContents) {
                try {
                    tasks.add(parseTask(s));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            try {
                Files.createFile(SAVE_FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the task to the text file in the user's hard drive.
     *
     * @param tasks task list.
     * @param isDeleted if the contents of the file should be deleted and reset.
     */
    public static void save(ArrayList<Task> tasks, boolean isDeleted) {
        // Saves to the file
        try {
            if (isDeleted) {
                // Clears the file
                resetTaskListFile();
                // Loop to print all the tasks into the file.
                for (Task t : tasks) {
                    Files.write(SAVE_FILE_PATH, (t.saveFormat() + System.lineSeparator()).getBytes(),
                            StandardOpenOption.APPEND);
                }
            } else {
                Task addedTask = tasks.get(tasks.size() - 1);
                Files.write(SAVE_FILE_PATH, (addedTask.saveFormat() + System.lineSeparator()).getBytes(),
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to parse the saved text file data into usable data to be input into the task list.
     *
     * @param savedTask the task in the saved task list.
     * @return a task to be input into the task list.
     * @throws DukeException if the method fails, printing the message output.
     */
    private static Task parseTask(String savedTask) throws DukeException {
        Task task = null;
        String[] taskArr;
        // savedTask is the string representation of the save file format of a task.
        taskArr = savedTask.split(" \\| ", 4);
        String taskType = taskArr[0]; // Todo, Event, Deadline
        String taskDone = taskArr[1]; // 0 for unmarked, 1 for marked.
        String taskDescription = taskArr[2]; // Description of the task.
        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription);
            break;

        case "E":
            String taskDuration = taskArr[3];
            LocalDateTime duration = LocalDateTime.parse(taskDuration, Task.OUTPUT_DATE_FORMAT);
            task = new Event(taskDescription, duration);
            break;

        case "D":
            String taskDue = taskArr[3];
            LocalDateTime due = LocalDateTime.parse(taskDue, Task.OUTPUT_DATE_FORMAT);
            task = new Deadline(taskDescription, due);
            break;
        default: {
            throw new DukeException("Something went wrong, please try again with correct formatting!");
        }
        }
        if (taskDone.equals("1")) {
            task.markDone();
        }

        return task;
    }

    /**
     * Method for resetting the file.
     */
    public static void resetTaskListFile() {
        try {
            Files.newBufferedWriter(SAVE_FILE_PATH, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
