package Duke.Storage;

import Duke.Exception.DukeException;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.ToDo;
import Duke.Task.Event;
import Duke.TaskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the long term Storage that application stores data in.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from existing data file.
     * If data file does not exist, create an empty new file.
     *
     * @return A List containing all the tasks stored in the data file.
     * @throws DukeException if file cannot be created or if there are tasks that cannot be loaded.
     */
    public List<Task> loadFile() throws DukeException {
        try {
            // Creates the output List
            List<Task> taskList = new ArrayList<>();

            File f = new File(this.filePath);

            // Create Duke.txt if it doesn't exist.
            if (!f.exists()) {
                boolean fileCreated = f.createNewFile();
                if (!fileCreated) {
                    throw DukeException.createStorageFileException();
                }
            }
            // Else read from Duke.txt and convert text to .
            else {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    Task newTask = loadTask(sc.nextLine());
                    taskList.add(newTask);
                }
                sc.close();
            }

            return taskList;
        } catch (IOException | DateTimeParseException | IllegalArgumentException e) {
            throw new DukeException("DukeError: Error loading file!");
        }
    }

    private Task loadTask(String taskStr) throws DukeException {
        try {
            String[] taskArray = taskStr.split("\\|");
            String taskTypeStr = taskArray[0];
            String isDoneStr = taskArray[1];
            String description = taskArray[2];

            Task newTask;
            switch (taskTypeStr) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    String byStr = taskArray[3];
                    LocalDateTime by = getDateTime(byStr);
                    newTask = new Deadline(description, by);
                    break;
                case "E":
                    String atStr = taskArray[3];
                    LocalDateTime at = getDateTime(atStr);
                    newTask = new Event(description, at);
                    break;
                default:
                    throw DukeException.readRowFromFileException(taskStr);
            }

            boolean isDone = isDoneStr.equals("X");
            if (isDone) {
                newTask.markAsDone();
            }

            return newTask;
        } catch (DukeException de) {
            throw DukeException.readRowFromFileException(
                    String.format("%s - %s", taskStr, de));
        } catch (Exception e) {
            throw DukeException.readRowFromFileException(taskStr);
        }
    }

    private LocalDateTime getDateTime(String datetimeStr) throws DukeException {
        try {
            return LocalDateTime.parse(
                    datetimeStr,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw DukeException.taskDateTimeException("Date time should be in the format yyyy-MM-dd HHmm!");
        }
    }

    /**
     * Saves the tasks from the taskList into the data file.
     *
     * @param taskList The list of tasks created by user.
     * @throws DukeException If data cannot be saved.
     */
    public void storeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save all data!");
        }

    }
}