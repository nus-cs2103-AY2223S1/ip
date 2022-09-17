package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents all of Duke's file operations.
 */
public class Storage {
    public static final String ERROR_INVALID_FILE_FORMAT = "Did you wrongly modify the file?";
    public static final String ERROR_INSUFFICIENT_PERMISSION = "Where are you running this file?";
    private String filePath;

    /**
     * Initialises Storage with filePath.
     *
     * @param filePath filePath of data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the specified filePath. Creates an empty file if not found.
     *
     * @return ArrayList containing Task used to initialise TaskList.
     * @throws DukeException if file was wrongly modified, causing errors in reading the file.
     */
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();;
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File dataFile = new File(this.filePath);
        try {
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    char firstLetter = line.charAt(0);
                    Task task;
                    switch (firstLetter) {
                    case 'T':
                        task = Todo.fromFileRepresentation(line);
                        break;
                    case 'E':
                        task = Event.fromFileRepresentation(line);
                        break;
                    case 'D':
                        task = Deadline.fromFileRepresentation(line);
                        break;
                    default:
                        throw new DukeException(Storage.ERROR_INVALID_FILE_FORMAT);
                    }
                    tasks.add(task);
                }
            } else {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("This should not happen... " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to specified filepath.
     *
     * @param tasks List of tasks.
     * @throws DukeException if Duke has insufficient permissions to write to file.
     */
    public void saveFile(TaskList tasks) throws DukeException {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fw.write(task.toFileRepresentation() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(Storage.ERROR_INSUFFICIENT_PERMISSION);
        }
    }
}
