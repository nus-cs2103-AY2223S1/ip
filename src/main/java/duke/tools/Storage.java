package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 * The TaskList is stored as a text file and is modified in real time with user command.
 */
public class Storage {
    /** Path to directory */
    private static Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");

    /** Path to file */
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
     * @throws DukeException If file is not found or data is in invalid format.
     */
    public TaskList loadFromFile() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            File data = new File(filePath.toString());
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                try {
                    String[] storedInfo = sc.nextLine().split(" \\| ");
                    String type = storedInfo[0];
                    boolean isDone = storedInfo[1].equals("O");
                    String description = storedInfo[2];
                    Task task;
                    switch (type) {
                    case "T":
                        task = new ToDo(description, isDone);
                        break;
                    case "D":
                        task = new Deadline(description, isDone, Parser.parseDate(storedInfo[3]));
                        break;
                    case "E":
                        task = new Event(description, isDone, Parser.parseDateTime(storedInfo[3]));
                        break;
                    default:
                        throw new DukeException("OOPS!!! No save data found");
                    }
                    taskList.add(task);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Incorrect task format");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! Data not found");
        }
        return new TaskList(taskList);
    }

    /**
     * Saves TaskList to file.
     *
     * @param taskList TaskList to be saved.
     * @throws DukeException If file access has failed.
     */
    public void saveToFile(TaskList taskList) throws DukeException {
        List<Task> dataToStore = taskList.getTaskList();
        checkDirectoryExist();
        checkFileExist();
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            for (int i = 0; i < taskList.getSize(); i++) {
                String dataToSave = dataToStore.get(i).taskToDataString();
                fw.write(dataToSave);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! File cannot be opened");
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
            throw new DukeException("OOPS!!! File cannot be opened");
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
            throw new DukeException("OOPS!!! Unable to create a new file. " +
                    "Tasks might not be stored.");
        }
    }
}
