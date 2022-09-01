package duke.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class encapsulating save and load logic.
 */
public class Storage {

    /**
     * Saves given task list into save file.
     * Throws DukeException if any error occurred while saving file.
     *
     * @param tasks TaskList containing tasks to be saved.
     * @Throws DukeException If any error occurred while saving file.
     */
    public void saveTaskListToFile(TaskList tasks) throws DukeException {
        String projectRoot = System.getProperty("user.dir");
        Path directoryPath = Path.of(projectRoot, "data");
        boolean directoryExists = Files.exists(directoryPath);

        // Create Folder if it does not exist
        if (!directoryExists) {
            try {
                Files.createDirectory(directoryPath);
            } catch (IOException e) {
                throw new DukeException("Error finding your save directory!");
            }
        }

        // Write to save file
        try {
            Path filePath = directoryPath.resolve("duke.txt");

            // Attempts to save tasks to save file
            for (int i = 1; i <= tasks.size(); i++) {
                // Format each task to its save format
                Task task = tasks.getTask(Integer.toString(i));
                String formattedTask = task.convertToSaveFormat();
                if (i == 1) { // First Task
                    Files.write(filePath, formattedTask.getBytes());
                } else {
                    formattedTask = System.lineSeparator() + formattedTask;
                    Files.write(filePath, formattedTask.getBytes(), StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error finding your save directory!");
        }
    }

    /**
     * Loads saved tasks from save file into given task list.
     *
     * @throws DukeException If unable to load tasks successfully.
     */
    public ArrayList<Task> readTaskListFromFile() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            String projectRoot = System.getProperty("user.dir");
            Path directoryPath = Path.of(projectRoot, "data");
            Path filePath = directoryPath.resolve("duke.txt");
            String[] lines = Files.lines(filePath).toArray(String[]::new);
            for (String l : lines) {
                taskList.add(parseSaveText(l));
            }
            return taskList;
        } catch (InvalidPathException | IOException | DukeException e) {
            taskList.clear();
            throw new DukeException("\nLooks like I can't find your old task list..."
                    + "\nGuess we'll have to start a new one!\n");
        }
    }

    /**
     * Returns Task corresponding to the given string input.
     * Throws DukeException if the given input is invalid.
     *
     * @param input String from which the task will be derived.
     * @return Task object corresponding to given input.
     * @throws DukeException If unable to parse task from input.
     */
    private Task parseSaveText(String input) throws DukeException {
        String[] taskProperties = input.split(" \\| ", 4);
        try {
            String taskType = taskProperties[0];
            String taskStatus = taskProperties[1];
            String taskName = taskProperties[2];
            Task task = null;

            switch (taskType) {
            case "T": {
                task = new ToDo(taskName);
                break;
            }
            case "E": {
                String taskTiming = taskProperties[3];
                LocalDateTime[] eventDates = DateTimeFormatUtils.parseDuration(taskTiming);
                task = new Event(taskName, eventDates[0], eventDates[1]);
                break;
            }
            case "D": {
                String taskTiming = taskProperties[3];
                LocalDateTime deadlineDate = DateTimeFormatUtils.parseDate(taskTiming);
                task = new Deadline(taskName, deadlineDate);
                break;
            }
            default: {
                throw new DukeException("Unexpected Error in parseSaveText");
            }
            }
            // Set task status
            if (taskStatus.equals("1")) {
                task.markAsDone();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException | DukeException e) {
            throw new DukeException("Error reading file");
        }
    }
}
