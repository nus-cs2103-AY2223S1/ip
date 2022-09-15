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
import duke.task.Priority;
import duke.task.Task;
import duke.task.ToDo;

/**
 * This class encapsulates the logic of the storage of the task list in the user's hard drive.
 */
public class Storage {
    /* Const fields for representation of the various paths. */
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final Path SAVE_LOCATION = Path.of(PROJECT_ROOT, "data");
    private static final String SAVE_FILE_NAME = "Task List.txt";
    private static final Path SAVE_FILE_PATH = (SAVE_LOCATION).resolve(SAVE_FILE_NAME);
    private static final String TASK_DONE = "1";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_PRIORITY_INDEX = 1;
    private static final int TASK_DONE_INDEX = 2;
    private static final int TASK_DESCRIPTION_INDEX = 3;
    private static final int TASK_TIME_INDEX = 4;


    /**
     * Loads the data from the file found in the saved location into the task list.
     *
     * @param tasks Task list.
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
     * @param tasks     Task list.
     * @param isDeleted If the contents of the file should be deleted and reset.
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
     * Parses the saved text file data into usable data to be input into the task list.
     *
     * @param savedTask Task in the saved task list.
     * @return Task to be input into the task list.
     * @throws DukeException If the method fails, prints the message output.
     */
    private static Task parseTask(String savedTask) throws DukeException {
        Task task;
        String[] taskArr;
        // savedTask is the string representation of the save file format of a task.
        taskArr = savedTask.split(" \\| ", 5);
        String taskType = taskArr[TASK_TYPE_INDEX]; // Todo, Event, Deadline
        String taskPriority = taskArr[TASK_PRIORITY_INDEX]; // HIGH, MEDIUM, LOW or NONE.
        String taskDone = taskArr[TASK_DONE_INDEX]; // 0 for unmarked, 1 for marked.
        String taskDescription = taskArr[TASK_DESCRIPTION_INDEX]; // Description of the task.
        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription, Priority.getPriority(taskPriority));
            break;

        case "E":
            String taskDuration = taskArr[TASK_TIME_INDEX];
            task = outputDateFormat(taskDescription, taskDuration, EVENT, taskPriority);
            break;

        case "D":
            String taskDue = taskArr[TASK_TIME_INDEX];
            task = outputDateFormat(taskDescription, taskDue, DEADLINE, taskPriority);
            break;
        default: {
            throw new DukeException("Something went wrong, save file might need to be deleted!");
        }
        }
        if (taskDone.equals(TASK_DONE)) {
            task.markDone();
        }
        return task;
    }

    /**
     * Resets the save file.
     */
    public static void resetTaskListFile() {
        try {
            Files.newBufferedWriter(SAVE_FILE_PATH, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs a task from the information from the save file inputted.
     *
     * @param taskDescription Description of the task.
     * @param taskTime Time of the task.
     * @param type Type of task.
     * @param priority Priority of the task.
     * @return Task as specified by the type.
     * @throws DukeException If an invalid save format is given.
     */
    private static Task outputDateFormat(String taskDescription, String taskTime, String type, String priority)
            throws DukeException {
        LocalDateTime time = LocalDateTime.parse(taskTime, Task.OUTPUT_DATE_FORMAT);
        if (type.equals("event")) {
            return new Event(taskDescription, time, Priority.getPriority(priority));
        } else if (type.equals("deadline")) {
            return new Deadline(taskDescription, time, Priority.getPriority(priority));
        } else {
            throw new DukeException("Something went wrong, unexpected todo");
        }
    }
}
