package scottie.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles storing and retrieving task data in a data file.
 */
class Storage {
    private static final Path TASKS_DATA_FILE_PATH = Paths.get("data", "tasks.txt");

    /**
     * Loads the task data lines from the data file.
     *
     * @return A List of lines from the data file.
     */
    List<String> loadTasksData() {
        try {
            if (Files.exists(TASKS_DATA_FILE_PATH)) {
                return Files.readAllLines(TASKS_DATA_FILE_PATH);
            } else {
                Files.createDirectories(TASKS_DATA_FILE_PATH.getParent());
                Files.createFile(TASKS_DATA_FILE_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    /**
     * Writes the given list of tasks to the data file.
     * The file is overwritten every time.
     *
     * @param tasks The list of tasks to write to the file.
     */
    void saveTasks(List<Task> tasks) {
        try {
            List<String> encodedTasks = tasks.stream().map(Task::toEncodedString).collect(Collectors.toList());
            Files.createDirectories(TASKS_DATA_FILE_PATH.getParent());
            Files.write(TASKS_DATA_FILE_PATH, encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
