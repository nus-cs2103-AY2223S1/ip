package maria.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import maria.Parser;
import maria.TaskManager;
import maria.task.Task;
import maria.task.TaskList;

/**
 * Converts between the storage state and the memory state for tasks.
 */
public class StorageConverter {

    /**
     * Converts the tasks in memory state to storage string.
     *
     * @param tasks The list of all the tasks
     * @return Storage string for the tasks
     */
    public static String tasksToString(TaskList tasks) {

        List<String> tasksStr = new ArrayList<>();
        for (Task task : tasks) {
            tasksStr.add(task.toStorageString());
        }
        String result = String.join("===", tasksStr);
        return result;

    }

    /**
     * Converts the storage string to the TaskList.
     *
     * @param taskManager The task manager for Maria, as defined in the {@code Launcher} class
     */
    public static void stringToTasks(TaskManager taskManager) {

        String tasksStr = null;
        try {
            tasksStr = taskManager.getStorage().readFromFile();
        } catch (IOException e) {
            System.out.println("File is corrupted. Please delete the tasks file and retry.");
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        String[] tasksStrArr = tasksStr.split("===");

        for (String taskStr : tasksStrArr) {
            if (!taskStr.isEmpty()) {
                Parser.parseStorage(taskStr).execute(taskManager);
            }
        }

    }

}
