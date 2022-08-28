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

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads a list of tasks from storage.
     * @param storage Hard disk storage that contains tasks.
     */
    public void loadTasksFromStorage(Storage storage) {
        if (this.size() != 0) {
            throw new DukeException("Current list of tasks is not empty!");
        }
        List<String> taskStrings = storage.read();
        for (String taskStr : taskStrings) {
            this.tasks.add(Parser.fromStorage(taskStr));
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskIndex Index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new TaskIndexOutOfBoundsException(taskIndex);
        }
        return this.tasks.remove(taskIndex - 1);
    }

    public void markTaskAsDone(int taskIndex) {
        this.getTask(taskIndex).markAsDone();
    }

    public void markTaskAsNotDone(int taskIndex) {
        this.getTask(taskIndex).markAsNotDone();
    }

    /**
     * Searches the list of tasks for a specified keyword.
     * @param keyword Keyword to search for.
     * @return A list of task indices representing the matches.
     */
    public List<Integer> search(String keyword) {
        ArrayList<Integer> matches = new ArrayList<>();
        for (int i = 1; i <= this.size(); i++) {
            if (this.getTask(i).toString().contains(keyword)) {
                matches.add(i);
            }
        }
        return matches;
    }

    public Task getTask(int i) {
        Task t;
        try {
            t = this.tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(i);
        }
        return t;
    }

    public int size() {
        return this.tasks.size();
    }
}
