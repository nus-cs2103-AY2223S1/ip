import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;

/**
 * Class encapsulating save and load logic.
 */
public class SaveUtils {

    /**
     * Saves given task list into save file.
     * Returns true if task list is saved successfully, false otherwise.
     *
     * @param taskList List of tasks to be saved.
     * @return True if completed successfully, false otherwise.
     */
    public static boolean saveTaskListToFile(ArrayList<Task> taskList) {
        String projectRoot = System.getProperty("user.dir");
        Path directoryPath = Path.of(projectRoot, "data");
        boolean directoryExists = Files.exists(directoryPath);

        // Create Folder if it does not exist
        if (!directoryExists) {
            try {
                Files.createDirectory(directoryPath);
            } catch (IOException e) {
                // Print Error Stack
                e.printStackTrace();
            }
        }

        // Add file
        try {
            Path filePath = directoryPath.resolve("duke.txt");

            // Attempts to save tasks to save file
            int currentTaskIndex = 0;
            for (Task t : taskList) {
                // Format each task to its save format
                String formattedTask = t.saveFormat();
                if (currentTaskIndex == 0) { // First Task
                    Files.write(filePath, formattedTask.getBytes());
                } else {
                    formattedTask = System.lineSeparator() + formattedTask;
                    Files.write(filePath, formattedTask.getBytes(), StandardOpenOption.APPEND);
                }

                currentTaskIndex++;
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Loads saved tasks from save file into given task list.
     *
     * @param taskList List to load tasks into.
     * @throws DukeException If unable to load tasks successfully.
     */
    public static void readTaskListFromFile(ArrayList<Task> taskList) throws DukeException {
        try {
            String projectRoot = System.getProperty("user.dir");
            Path directoryPath = Path.of(projectRoot, "data");
            Path filePath = directoryPath.resolve("duke.txt");
            String[] lines = Files.lines(filePath).toArray(String[]::new);
            for (String l : lines) {
                taskList.add(parseText(l));
            }
        } catch (InvalidPathException | IOException | DukeException e) {
            taskList.clear();
            throw new DukeException(e.getMessage());
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
    private static Task parseText(String input) throws DukeException {
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
                task = new Event(taskName, taskTiming);
                break;
            }
            case "D": {
                String taskTiming = taskProperties[3];
                task = new Deadline(taskName, taskTiming);
                break;
            }
            }

            // Set task status
            if (taskStatus.equals("1")) {
                task.markAsDone();
            }

            return task;

        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

            throw new DukeException("Error reading file");
        }
    }
}
