package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.DukeResponse;

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
     *
     * @param index Index as a string.
     * @return Task if successful.
     * @throws DukeException if not successful.
     */
    public static Task getTask(String index) throws DukeException {
        try {
            int idx = Integer.parseInt(index);
            return taskList.get(idx - 1);
        } catch (NumberFormatException ex) {
            throw new DukeException(new DukeResponse(
                    "Sorry, I didn't understand " + index + ", please give me a number."));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(new DukeResponse(
                    "Sorry, the number " + index + ", wasn't in the range."));
        }
    }

    /**
     * Gets tasks that match the search term.
     *
     * @param query Search term.
     * @return List of tasks.
     */
    public static List<Task> filterTasks(String query) {
        return taskList.stream()
                .filter((task) -> task.isMatchingQuery(query))
                .collect(Collectors.toList());
    }

    /**
     * Gets the task list for other classes to work on.
     *
     * @return The task list.
     */
    public static List<Task> getTaskList() {
        return taskList;
    }
}
