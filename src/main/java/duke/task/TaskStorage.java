package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;

/**
 * Accesses a file for tasks.
 */
public class TaskStorage {

    /**
     * Gets ArrayList of previously saved tasks.
     *
     * @return ArrayList of tasks.
     */
    public static ArrayList<Task> getTasks() {
        Storage storage = Storage.getFileState("./duke.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        for (String[] line : storage.getLines()) {
            TaskFactory.constructOptionalTask(line).ifPresent((task) -> tasks.add(task));
        }
        return tasks;
    }

    /**
     * Saves a list of tasks to the default file.
     *
     * @param tasks List of tasks.
     */
    public static void saveTasks(List<Task> tasks) {
        List<String[]> lines = tasks.stream().map(Task::getAsStringArray).collect(Collectors.toList());
        Storage storage = Storage.getFileState("./duke.txt");
        storage.saveLines((String[][]) lines.toArray(new String[][]{}));
    }
}
