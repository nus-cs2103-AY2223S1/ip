package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Storage object used for file operations such as read and write of TaskList to text file. */
public class Storage {

    private Ui ui;
    private String filePath;

    /**
     * Returns a Storage object.
     *
     * @param filePath File path of the text file used for storage.
     */
    public Storage(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
    }

    /**
     * Reads from the text file in the specified file path.
     * Scan through every line in file and creates the relevant Task objects based on the task type.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> loadTaskList() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file;
        try {
            file = new File(this.filePath);
            if (file.isDirectory()) {
                throw new DukeException("☹ OOPS!!! Invalid file path, path given is a directory");
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
                            task = new Todo(splitString[2]);
                            break;
                        case "D": {
                            task = new Deadline(splitString[2], splitString[3]);
                            break;
                        }
                        case "E": {
                            task = new Event(splitString[2], splitString[3]);
                            break;
                        }
                        default:
                            throw new DukeException("☹ OOPS!!! Invalid task type found in file!");
                        }

                        if ((splitString[1] == "1")) {
                            task.setTaskStatus(true);
                        } else {
                            task.setTaskStatus(false);
                        }

                        taskList.add(task);
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    throw new DukeException("☹ OOPS!!! File could not be found");
                }
            }
        } catch (DukeException exception) {
            ui.printMessage(exception.toString());
        } finally {
            return taskList;
        }
    }

    /**
     * Saves the current TaskList into the text file located at file path specified by the user.
     *
     * @param currTaskList Current TaskList to be saved
     */
    public void saveTaskList(TaskList currTaskList) {
        File file;
        ArrayList<Task> taskList = currTaskList.getTaskList();
        try {
            file = new File(this.filePath);
            if (file.isDirectory()) {
                throw new DukeException("☹ OOPS!!! Invalid file path, path given is a directory");
            }

            if (!file.exists()) {
                try {
                    if (!file.getParentFile().mkdirs()) {
                        throw new DukeException("☹ OOPS!!! Directory could not be created");
                    }

                    if (!file.createNewFile()) {
                        throw new DukeException("☹ OOPS!!! File could not be created");
                    }
                } catch (IOException exception) {
                    throw new DukeException("☹ OOPS!!! Something went wrong when trying to create file. Error message: "
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

        } catch (DukeException exception) {
            ui.printMessage(exception.toString());
        }
    }
}
