package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The Storage class that stores the tasks in TaskList.
 *
 * CS2103T iP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Storage {
    /** Save location of TaskList */
    private File file;
    private String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath Location of save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);

        assert hasValidState() : "Construction Failed: Invalid state";
    }

    /**
     * Saves tasks into save file.
     *
     * @param taskList The List of Task to write from.
     */
    public void save(TaskList taskList) throws DukeException {
        // Write into File
        try {
            makeFileIfDoesNotExist();
            writeToFile(taskList);
        } catch (DukeException e) {
            throw new DukeException("Error Saving Tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from save file.
     */
    public List<Task> load() throws DukeException {
        if (!this.file.exists()) {
            throw new DukeException("Failed to load File: Save file does not exist");
        }

        try {
            List<Task> tasks = new ArrayList<>();
            readFromFile(tasks);

            return tasks;
        } catch (DukeException e) {
            throw new DukeException("Failed to Load File: " + e.getMessage());
        }
    }

    /**
     * Creates Directory if it does not exist
     *
     * @return true if directory exists or is created, false otherwise.
     * @throws DukeException Exception thrown when directory could not be created.
     */
    private boolean makeDirectoryIfDoesNotExist() throws DukeException {
        try {
            File directory = new File(this.file.getParent());
            boolean isDirectoryCreated = directory.exists();

            if (!isDirectoryCreated) {
                boolean isSuccess = directory.mkdirs();
                return isSuccess;
            } else {
                return true;
            }
        } catch (SecurityException e) {
            throw new DukeException("Insufficient Permission to create and read file");
        }
    }

    /**
     * Creates File in directory if it does not exist.
     *
     * @return True if file exist or is created, false otherwise.
     * @throws DukeException Exception thrown when file could not be created.
     */
    private boolean makeFileIfDoesNotExist() throws DukeException {
        try {
            makeDirectoryIfDoesNotExist();

            boolean isFileCreated = this.file.exists();

            if (!isFileCreated) {
                boolean isSuccess = this.file.createNewFile();
                return isSuccess;
            } else {
                return true;
            }
        } catch (IOException e) {
            throw new DukeException("Cant create Save File.");
        }
    }

    /**
     * Writes data into save file.
     *
     * @param taskList The list which data is read from.
     * @throws DukeException Exception thrown when data could not be saved.
     */
    private void writeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            List<Task> tasks = taskList.getTasks();

            for (Task task : tasks) {
                String saveData = task.stringify();
                bufferedWriter.write(saveData);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException("Failure writing data to file");
        }
    }

    /**
     * Reads save data into list of tasks.
     * @param tasks The list where data is stored.
     * @throws DukeException Exception thrown when data is unreadable.
     */
    private void readFromFile(List<Task> tasks) throws DukeException {
        try {
            FileReader fileReader = new FileReader(this.file.getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] taskData = line.split(" \\| ");
                String type = taskData[0];

                switch (type) {
                case "T":
                    addTodo(tasks, taskData);
                    break;
                case "E":
                    addEvent(tasks, taskData);
                    break;
                case "D":
                    addDeadline(tasks, taskData);
                    break;
                default:
                    throw new DukeException("Invalid String in load file.");
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException | DateTimeParseException e) {
            throw new DukeException("Invalid String in load file.");
        }
    }

    /**
     * Implements the class invariant.
     *
     * Perform all checks on the state of the object.
     * One may assert that this method returns true at the end
     * of every public method.
     * @return true if valid State, false otherwise.
     */
    private boolean hasValidState() {
        return isValidFilePath(this.filePath) && isValidFile(this.file);
    }

    /**
     * Returns validity of filePath.
     *
     * @param filePath The filePath to store Save File.
     * @return true if valid filePath, false otherwise.
     */
    private boolean isValidFilePath(String filePath) {
        return !filePath.isEmpty();
    }

    /**
     * Returns validity of File.
     *
     * @param file The Save File.
     * @return true if valid file, false otherwise.
     */
    private boolean isValidFile(File file) {
        return file != null;
    }

    /**
     * Adds Deadline into tasks when reading from save file.
     * @param tasks The list that Deadline is added to.
     * @param taskData The Data read from save file.
     */
    private void addDeadline(List<Task> tasks, String[] taskData) {
        String description = taskData[1];
        String doneStatus = taskData[2];
        String dateTime = taskData[3];
        LocalDateTime dueDate = LocalDateTime.parse(dateTime);
        Deadline deadlineTask = new Deadline(description, doneStatus.equals("X"), dueDate);
        tasks.add(deadlineTask);
    }

    /**
     * Adds Event into tasks when reading from save file.
     * @param tasks The list that Event is added to.
     * @param taskData The Data read from save file.
     */
    private void addEvent(List<Task> tasks, String[] taskData) {
        String description = taskData[1];
        String doneStatus = taskData[2];
        String dateTime = taskData[3];
        LocalDateTime eventDate = LocalDateTime.parse(dateTime);
        Event eventTask = new Event(description, doneStatus.equals("X"), eventDate);
        tasks.add(eventTask);
    }

    /**
     * Adds Todo into tasks when reading from save file.
     * @param tasks The list that Todo is added to.
     * @param taskData The Data read from save file.
     */
    private void addTodo(List<Task> tasks, String[] taskData) {
        String description = taskData[1];
        String doneStatus = taskData[2];
        ToDo toDoTask = new ToDo(description, doneStatus.equals("X"));
        tasks.add(toDoTask);
    }
}
