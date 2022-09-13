package duke.tools;

import static duke.tasks.Task.TaskType.parseTaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeCannotCreateFileException;
import duke.exceptions.DukeCannotOpenFileException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownDataFormatException;
import duke.exceptions.DukeUnknownTaskTypeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Task.TaskType;
import duke.tasks.Todo;

/**
 * This class manages the storage of the user's task list.
 * The list of tasks are stored as a text file and is being modified in-time with the user's commands.
 */
public class Storage {

    /** Text icon to signify a task is done. */
    private static final String IS_DONE_YES = "Y";
    /** Text icon to signify a task is not done. */
    private static final String IS_DONE_NO = "N";
    /** Text separator for datetime of a deadline task. */
    private static final String DEADLINE_SEPARATOR = "/by ";
    /** Text separator for datetime of an event. */
    private static final String EVENT_SEPARATOR = "/at ";
    /** The path to the directory for storing Duke data. */
    private static final Path DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"), "data");
    /** The path to the file storing Duke data. */
    private static final Path FILE_PATH = DIRECTORY_PATH.resolve("data.txt");

    /**
     * Loads the previously stored TaskList from the file.
     *
     * @return The TaskList stored on the file.
     * @throws DukeException When the data file cannot be accessed.
     */
    public TaskList loadFromFile() throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            File data = new File(FILE_PATH.toString());
            Scanner sc = new Scanner(data);
            List<Task> storedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                storedTasks.add(convertDataStringToTask(sc.nextLine()));
            }
            sc.close();
            return new TaskList(storedTasks);
        } catch (FileNotFoundException e) {
            throw new DukeCannotOpenFileException();
        }
    }

    /**
     * Stores the TaskList on the file.
     *
     * @param taskList The TaskList to be stored on the file.
     * @throws DukeException When the data file cannot be accessed.
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        List<Task> storedTasks = taskList.getStoredTasks();
        try {
            FileWriter data = new FileWriter(FILE_PATH.toString());
            for (Task storedTask : storedTasks) {
                data.write(convertTaskToDataString(storedTask));
            }
            data.close();
        } catch (IOException e) {
            throw new DukeCannotOpenFileException();
        }
    }

    /**
     * Appends a task at the end of the storage file.
     *
     * @param task The task to append in the file.
     * @throws DukeException When the data file cannot be accessed.
     */
    public void appendToFile(Task task) throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter data = new FileWriter(FILE_PATH.toString(), true);
            data.write(convertTaskToDataString(task));
            data.close();
        } catch (IOException e) {
            throw new DukeCannotOpenFileException();
        }
    }

    /**
     * Ensures that the directory for storing Duke data exists.
     * If it does not exist, creates the directory at the path.
     */
    private static void ensureDirectoryExist() {
        File folder = new File(DIRECTORY_PATH.toString());
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Ensures that the file storing Duke data exists.
     * If it does not exist, creates the file at the path.
     *
     * @throws DukeException When the data file cannot be created.
     */
    private void ensureFileExist() throws DukeException {
        try {
            File data = new File(DIRECTORY_PATH.resolve("data.txt").toString());
            if (!data.exists()) {
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeCannotCreateFileException();
        }
    }

    /**
     * Converts a task to a String that can be stored on the file.
     *
     * @param task The task to be stored on file.
     * @return The String representing how the data is stored on the file.
     * @throws DukeException When encountering an unknown task type.
     */
    private static String convertTaskToDataString(Task task) throws DukeException {
        switch (task.getTaskType()) {
        case TODO:
            return TaskType.TODO.value + " " + (task.isDone() ? IS_DONE_YES : IS_DONE_NO) + " "
                    + task.getDescription() + System.lineSeparator();
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return TaskType.DEADLINE.value + " " + (deadline.isDone() ? IS_DONE_YES : IS_DONE_NO) + " "
                    + deadline.getDescription() + DEADLINE_SEPARATOR + deadline.getDateTime() + System.lineSeparator();
        case EVENT:
            Event event = (Event) task;
            return TaskType.EVENT.value + " " + (event.isDone() ? IS_DONE_YES : IS_DONE_NO) + " "
                    + event.getDescription() + EVENT_SEPARATOR + event.getDateTime() + System.lineSeparator();
        default:
            throw new DukeUnknownTaskTypeException();
        }
    }

    /**
     * Reads a String data and converts it back to a Task object.
     *
     * @param str The String representing how the data is stored on the file.
     * @return The Task object represented by the String.
     * @throws DukeException When encountering an unknown data string format
     */
    private static Task convertDataStringToTask(String str) throws DukeException {
        String[] taskInfo = str.split(" ", 3);
        try {
            TaskType type = parseTaskType(taskInfo[0]);
            switch (type) {
            case TODO:
                Todo todo = new Todo(taskInfo[2]);
                if (IS_DONE_YES.equals(taskInfo[1].strip())) {
                    todo.setIsDone(true);
                }
                return todo;
            case DEADLINE:
                String[] deadlineInfo = taskInfo[2].split(DEADLINE_SEPARATOR, 2);
                Deadline deadline = new Deadline(deadlineInfo[0].strip(), LocalDateTime.parse(deadlineInfo[1]));
                if (IS_DONE_YES.equals(taskInfo[1])) {
                    deadline.setIsDone(true);
                }
                return deadline;
            case EVENT:
                String[] eventInfo = taskInfo[2].split(EVENT_SEPARATOR, 2);
                Event event = new Event(eventInfo[0].strip(), LocalDateTime.parse(eventInfo[1]));
                if (IS_DONE_YES.equals(taskInfo[1])) {
                    event.setIsDone(true);
                }
                return event;
            default:
                throw new DukeUnknownDataFormatException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeUnknownDataFormatException();
        }
    }
}
