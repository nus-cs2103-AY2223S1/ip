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

import duke.exceptions.DukeException;
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

    /** The path to the directory for storing Duke data. */
    private static Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");

    /** The path to the file storing Duke data. */
    private Path filePath;

    public Storage() {
        this.filePath = directoryPath.resolve("data.txt");
    }

    public Storage(String fileName) {
        this.filePath = directoryPath.resolve(fileName);
    }

    /**
     * Loads the previously stored TaskList from the file.
     *
     * @return The TaskList stored on the file.
     * @throws DukeException
     */
    public TaskList loadFromFile() throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            File data = new File(filePath.toString());
            Scanner sc = new Scanner(data);
            List<Task> storedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                storedTasks.add(convertDataStringToTask(sc.nextLine()));
            }
            sc.close();
            return new TaskList(storedTasks);
        } catch (FileNotFoundException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    /**
     * Stores the TaskList on the file.
     *
     * @param taskList The TaskList to be stored on the file.
     * @throws DukeException
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        List<Task> storedTasks = taskList.getStoredTasks();
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter data = new FileWriter(filePath.toString());
            for (int i = 0; i < storedTasks.size(); i++) {
                data.write(convertTaskToDataString(storedTasks.get(i)));
            }
            data.close();
        } catch (IOException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    /**
     * Appends a task at the end of the storage file.
     *
     * @param task The task to append in the file.
     * @throws DukeException
     */
    public void appendToFile(Task task) throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter data = new FileWriter(filePath.toString(), true);
            data.write(convertTaskToDataString(task));
            data.close();
        } catch (IOException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    /**
     * Ensures that the directory for storing Duke data exists.
     * If it does not exist, creates the directory at the path.
     */
    private static void ensureDirectoryExist() {
        File folder = new File(directoryPath.toString());
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Ensures that the file storing Duke data exists.
     * If it does not exist, creates the file at the path.
     */
    private void ensureFileExist() throws DukeException {
        try {
            File data = new File(directoryPath.resolve("data.txt").toString());
            if (!data.exists()) {
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Exception: Unable to create new file. Tasks might not be stored.");
        }
    }

    /**
     * Converts a task to a String that can be stored on the file.
     *
     * @param task The task to be stored on file.
     * @return The String representing how the data is stored on the file.
     * @throws DukeException
     */
    private static String convertTaskToDataString(Task task) throws DukeException {
        switch (task.getTaskType()) {
        case TODO:
            return "T " + (task.isDone() ? "Y " : "N ") + task.getDescription() + System.lineSeparator();
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return "D " + (deadline.isDone() ? "Y " : "N ") + deadline.getDescription()
                    + "/by " + deadline.getDateTime() + System.lineSeparator();
        case EVENT:
            Event event = (Event) task;
            return "E " + (event.isDone() ? "Y " : "N ") + event.getDescription()
                    + "/at " + event.getDateTime() + System.lineSeparator();
        default:
            throw new DukeException("Exception: Unknown task type.");
        }
    }

    /**
     * Reads a String data and converts it back to a Task object.
     *
     * @param str The String representing how the data is stored on the file.
     * @return The Task object represented by the String.
     * @throws DukeException
     */
    private static Task convertDataStringToTask(String str) throws DukeException {
        String[] taskInfo = str.split(" ", 3);
        try {
            TaskType type = parseTaskType(taskInfo[0]);
            switch (type) {
            case TODO:
                Todo todo = new Todo(taskInfo[2]);
                if ("Y".equals(taskInfo[1].strip())) {
                    todo.setIsDone(true);
                }
                return todo;
            case DEADLINE:
                String[] deadlineInfo = taskInfo[2].split("/by ", 2);
                Deadline deadline = new Deadline(deadlineInfo[0].strip(), LocalDateTime.parse(deadlineInfo[1]));
                if ("Y".equals(taskInfo[1])) {
                    deadline.setIsDone(true);
                }
                return deadline;
            case EVENT:
                String[] eventInfo = taskInfo[2].split("/at ", 2);
                Event event = new Event(eventInfo[0].strip(), LocalDateTime.parse(eventInfo[1]));
                if ("Y".equals(taskInfo[1])) {
                    event.setIsDone(true);
                }
                return event;
            default:
                throw new DukeException("Exception: Incorrect task format");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Incorrect task format.");
        }
    }
}
