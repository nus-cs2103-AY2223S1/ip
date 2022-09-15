package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.duke.DukeException;
import duke.task.*;

/** Storage object used for file operations such as read and write of TaskList to text file. */
public class Storage {

    private String filePath;

    /**
     * Returns a Storage object.
     *
     * @param filePath File path of the text file used for storage.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "Filepath should not be empty";
        this.filePath = filePath;
    }

    /**
     * Reads from the text file in the specified file path.
     * Scan through every line in file and creates the relevant Task objects based on the task type.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> loadTaskList() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file;
        file = new File(this.filePath);
        if (file.isDirectory()) {
            throw new DukeException("OOPS!!! Invalid file path, path given is a directory");
        }
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    String[] splitString = nextLine.split("\\|");

                    Task task;
                    switch (splitString[0]) {
                    case "T":
                        task = new Todo(splitString[3]);
                        break;
                    case "D": {
                        task = new Deadline(splitString[3], splitString[4]);
                        break;
                    }
                    case "E": {
                        task = new Event(splitString[3], splitString[4]);
                        break;
                    }
                    default:
                        throw new DukeException("OOPS!!! Invalid task type found in file!");
                    }

                    if ((splitString[1] == "1")) {
                        task.setTaskStatus(true);
                    } else {
                        task.setTaskStatus(false);
                    }

                    task.setPriority(Integer.parseInt(splitString[2]));

                    taskList.add(task);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                throw new DukeException("OOPS!!! File could not be found");
            }
        }

        return taskList;

    }

    /**
     * Saves the current TaskList into the text file located at file path specified by the user.
     *
     * @param currTaskList Current TaskList to be saved
     */
    public void saveTaskList(TaskList currTaskList) throws DukeException {
        File file;
        ArrayList<Task> taskList = currTaskList.getTaskList();

        file = new File(this.filePath);

        if (file.isDirectory()) {
            throw new DukeException("OOPS!!! Invalid file path, path given is a directory");
        }

        if (!file.exists()) {
            try {
                //Solution below adapted from https://stackoverflow.com/a/4040667
                if (!file.getParentFile().mkdirs()) {
                    throw new DukeException("OOPS!!! Directory could not be created");
                }

                if (!file.createNewFile()) {
                    throw new DukeException("OOPS!!! File could not be created");
                }
            } catch (IOException exception) {
                throw new DukeException("OOPS!!! Something went wrong when trying to create file. Error message: "
                        + exception.getMessage());
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskList) {
                //lineSeparator used to support multiple systems
                fileWriter.write(task.toFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException exception) {
            throw new DukeException("☹ OOPS!!! Could not be written to file. Error message: "
                    + exception.getMessage());
        }
    }
}
