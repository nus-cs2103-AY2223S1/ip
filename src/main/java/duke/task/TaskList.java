package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duke.Ui;

/**
 * Holds the list of tasks
 */
public class TaskList {
    /** List of tasks to remember */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Initializes the task list.
     */
    public static void initializeTaskList() {
        taskList = TaskStorage.getTasks();
    }

    /**
     * Finalizes the task list.
     */
    public static void finalizeTaskList() {
        TaskStorage.saveTasks(taskList);
    }

    /**
     * Gets task from index as string.
     * @param index Index as a string.
     * @return Optional.of(task) if successful, else Optional.empty().
     */
    public static Optional<Task> getTask(String index) {
        try {
            int idx = Integer.parseInt(index);
            Task task = taskList.get(idx - 1);
            return Optional.of(task);
        } catch (NumberFormatException ex) {
            Ui.messagePrint("Sorry, I didn't understand " + index + ", please give me a number.");
            return Optional.empty();
        } catch (IndexOutOfBoundsException ex) {
            Ui.messagePrint("Sorry, the number " + index + ", wasn't in the range.");
            return Optional.empty();
        }
    }

    /**
     * Gets the task list for other classes to work on.
     * @return The task list.
     */
    public static List<Task> getTaskList() {
        return taskList;
    }
}
