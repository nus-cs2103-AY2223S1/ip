package anthea.task;

import java.util.ArrayList;
import java.util.List;

import anthea.ChatbotResponse;
import anthea.Pair;
import anthea.exception.ChatbotException;

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
     * @throws ChatbotException if not successful.
     */
    public static Task getTask(String index) throws ChatbotException {
        try {
            int idx = Integer.parseInt(index);
            return taskList.get(idx - 1);
        } catch (NumberFormatException ex) {
            throw new ChatbotException(new ChatbotResponse(
                    "Sorry, I didn't understand " + index + ", please give me a number."));
        } catch (IndexOutOfBoundsException ex) {
            throw new ChatbotException(new ChatbotResponse(
                    "Sorry, the number " + index + ", wasn't in the range."));
        }
    }

    /**
     * Gets tasks that match the search term.
     *
     * @param query Search term.
     * @return List of indices and tasks.
     */
    public static ArrayList<Pair<Integer, Task>> filterTasks(String query) {
        ArrayList<Pair<Integer, Task>> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.isMatchingQuery(query)) {
                result.add(new Pair<>(i, task));
            }
        }
        return result;
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
