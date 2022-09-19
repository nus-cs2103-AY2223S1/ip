package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Loads a list of tasks from storage.
     *
     * @param storage Hard disk storage that contains tasks.
     * @return Number of loaded tasks.
     */
    public int loadTasksFromStorage(Storage storage) {
        assert storage != null : "Storage is null";
        if (size() != 0) {
            throw new DukeException("Current list of tasks is not empty!");
        }
        List<String> taskStrings = storage.read();
        for (String taskStr : taskStrings) {
            tasks.add(Parser.fromStorage(taskStr));
        }
        return taskStrings.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t Task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskIndex Index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int taskIndex) {
        boolean validTaskIndex = taskIndex >= 1 && taskIndex <= size();
        if (!validTaskIndex) {
            throw new TaskIndexOutOfBoundsException(taskIndex);
        }
        return tasks.remove(taskIndex - 1);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskIndex Index of the task to mark as done.
     */
    public void markTaskAsDone(int taskIndex) {
        getTask(taskIndex).markAsDone();
    }

    /**
     * Marks a task in the list as not done yet.
     *
     * @param taskIndex Index of the task to mark as not done yet.
     */
    public void markTaskAsNotDone(int taskIndex) {
        getTask(taskIndex).markAsNotDone();
    }

    /**
     * Searches the list of tasks for a specified keyword.
     *
     * @param keyword Keyword to search for.
     * @return A list of task indices representing the matches.
     */
    public List<Integer> search(String keyword) {
        ArrayList<Integer> matches = new ArrayList<>();
        for (int i = 1; i <= size(); i++) {
            if (getTask(i).toString().contains(keyword)) {
                matches.add(i);
            }
        }
        return matches;
    }

    /**
     * Gets the task at a specified index.
     *
     * @param i Index of the task.
     * @return Task at the index.
     */
    public Task getTask(int i) {
        Task t;
        try {
            t = tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(i);
        }
        return t;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}
