package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

/**
 * Represents the long term Storage that application stores data in.
 */
public class Storage {
    public final String filePath;

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

            assert this.filePath != null: "Filepath cannot be null";
            File f = new File(this.filePath);

            // Create Duke.txt if it doesn't exist.
            if (!f.exists()) {
                boolean fileCreated = f.createNewFile();
                if (!fileCreated) {
                    throw DukeException.createStorageFileException();
                }

                // Else read from Duke.txt and convert text to .
            } else {
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
            assert taskStr != null: "String to load task cannot be null";
            String[] tokens = tokenize(taskStr);
            String taskTypeStr = getTaskTypeStr(tokens);
            String description = getDescription(tokens);
            String[] tags = getTags(tokens);

            Task newTask;
            switch (taskTypeStr) {
            case "T":
                newTask = loadTodo(description);
                break;
            case "D":
                LocalDateTime deadlineDateTime = getDateTime(tokens);
                newTask = loadDeadline(description, deadlineDateTime);
                break;
            case "E":
                LocalDateTime eventDateTime = getDateTime(tokens);
                newTask = loadEvent(description, eventDateTime);
                break;
            default:
                throw DukeException.readRowFromFileException(taskStr);
            }

            modifyDone(newTask, isDone(tokens));
            modifyTags(newTask, tags);
            return newTask;
        } catch (DukeException de) {
            throw DukeException.readRowFromFileException(
                    String.format("%s - %s", taskStr, de));
        } catch (Exception e) {
            throw DukeException.readRowFromFileException(taskStr);
        }
    }

    private String[] tokenize(String taskStr) throws DukeException {
        if (!taskStr.contains("|")) {
            throw new DukeException("Task String does not contain the separator.");
        }
        return taskStr.split("\\|");
    }

    private String getTaskTypeStr(String[] tokens) throws DukeException {
        return tokens[0];
    }

    private boolean isDone(String[] tokens) throws DukeException {
        String isDoneStr = tokens[1];
        return isDoneStr.equals("X");
    }

    private String getDescription(String[] tokens) throws DukeException {
        return tokens[2];
    }

    private LocalDateTime getDateTime(String[] tokens) throws DukeException {
        String datetimeStr = tokens[4];
        return getDateTime(datetimeStr);
    }

    private LocalDateTime getDateTime(String datetimeStr) throws DukeException {
        try {
            assert datetimeStr != null: "String to read datetime from cannot be null";
            return LocalDateTime.parse(
                    datetimeStr,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw DukeException.taskDateTimeException("Date time should be in the format yyyy-MM-dd HHmm!");
        }
    }

    private String[] getTags(String[] tokens) {
        String tokenString = tokens[3];
        return tokenString.split(",");
    }

    private Task loadTodo(String description) throws DukeException {
        return new ToDo(description);
    }

    private Task loadDeadline(String description, LocalDateTime datetime) throws DukeException {
        return new Deadline(description, datetime);
    }

    private Task loadEvent(String description, LocalDateTime datetime) throws DukeException {
        return new Event(description, datetime);
    }

    private void modifyDone(Task newTask, boolean isDone) {
        if (isDone) {
            newTask.markAsDone();
        }
    }

    private void modifyTags(Task newTask, String[] tags) {
        for (String tag: tags) {
            newTask.addTag(tag);
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
            assert taskList != null: "TaskList to be stored cannot be null";
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save all data!");
        }

    }
}
