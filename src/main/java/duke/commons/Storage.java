package duke.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 * The TaskList is stored as a text file and is modified in real time with user command.
 */
public class Storage {
    private static final String MESSAGE_FILE_CANNOT_BE_OPEN = "OOPS!!! File cannot be opened";
    private static final String MESSAGE_FILE_ACCESS_FAILURE = "OOPS!!! Unable to create a new file. "
            + "Tasks might not be stored.";
    private static final String MESSAGE_INVALID_DATA_FORMAT = "OOPS!!! Invalid data format";
    private static final String MESSAGE_NO_SAVE_DATA = "OOPS!!! No save data found";
    private static Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");
    private Path filePath;

    /**
     * Constructs a default storage and initialises path to file.
     */
    public Storage() {
        filePath = directoryPath.resolve("data.txt");
    }

    /**
     * Constructs a storage with path to specified file.
     *
     * @param fileName String name of file.
     */
    public Storage(String fileName) {
        filePath = directoryPath.resolve(fileName);
    }

    /**
     * Loads previously stored data from file into current TaskList.
     *
     * @return TaskList stored in the file.
     * @throws DukeException If file is not found.
     */
    public TaskList loadFromFile() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            File data = new File(filePath.toString());
            Scanner dataScanner = new Scanner(data);
            while (dataScanner.hasNextLine()) {
                readDataToTask(dataScanner, taskList);
            }
            dataScanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_FILE_CANNOT_BE_OPEN);
        }
        return new TaskList(taskList);
    }

    /**
     * Reads data strings and converts it back into task object to be stored in
     * the TaskList.
     *
     * @param dataScanner Scanner to read data string.
     * @param taskList TaskList to store the task.
     * @throws DukeException If no data string is found or data is in invalid format.
     */
    private void readDataToTask(Scanner dataScanner, List<Task> taskList) throws DukeException {
        String separator = "\\|";
        String dataTaskIsDone = "O";
        try {
            String[] storedInfo = dataScanner.nextLine().split(separator);
            String taskIcon = storedInfo[0].strip();
            boolean isDone = (storedInfo[1].strip()).equals(dataTaskIsDone);
            String description = storedInfo[2].strip();
            Task task;
            switch (taskIcon) {
            case ToDo.TASK_ICON:
                task = new ToDo(description, isDone);
                break;
            case Deadline.TASK_ICON:
                task = new Deadline(description, isDone, Parser.parseLocalDate(storedInfo[3]));
                break;
            case Event.TASK_ICON:
                task = new Event(description, isDone, Parser.parseLocalDateTime(storedInfo[3]));
                break;
            default:
                throw new DukeException(MESSAGE_NO_SAVE_DATA);
            }
            taskList.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_DATA_FORMAT);
        }
    }

    //@@author Bubbl3T-reused
    //Reused from https://github.com/Bubbl3T/ip/blob/master/src/main/java/duke/tools/Storage.java
    // with minor modifications to the code.
    /**
     * Saves TaskList to file.
     *
     * @param taskList TaskList to be saved.
     * @throws DukeException If file access has failed.
     */
    public void saveToFile(TaskList taskList) throws DukeException {
        checkDirectoryExist();
        checkFileExist();
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            for (int i = 0; i < taskList.getSize(); i++) {
                String dataToSave = taskList.getTask(i).taskToDataString();
                fw.write(dataToSave);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(MESSAGE_FILE_CANNOT_BE_OPEN);
        }
    }

    /**
     * Appends task to end of file.
     *
     * @param task Task to be appended.
     * @throws DukeException If file access has failed.
     */
    public void appendToFile(Task task) throws DukeException {
        checkDirectoryExist();
        checkFileExist();
        try {
            FileWriter data = new FileWriter(filePath.toString(), true);
            data.write(task.taskToDataString());
            data.close();
        } catch (IOException e) {
            throw new DukeException(MESSAGE_FILE_CANNOT_BE_OPEN);
        }
    }


    /**
     * Checks that directory exists, else make a directory at the path.
     */
    private static void checkDirectoryExist() {
        File folder = new File(directoryPath.toString());
        if (!folder.exists()) {
            folder.mkdir();
        }
        assert folder.exists() : "Directory should exist";
    }

    /**
     * Checks that file exists, else create a file at the path.
     *
     * @throws DukeException If file access has failed.
     */
    private void checkFileExist() throws DukeException {
        try {
            File data = new File(directoryPath.resolve("data.txt").toString());
            if (!data.exists()) {
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(MESSAGE_FILE_ACCESS_FAILURE);
        }
    }
    //@@author
}
