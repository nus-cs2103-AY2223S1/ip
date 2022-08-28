package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.parser.Parser;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a list of tasks from a list of strings stored in the hard disk.
     * @param taskStrings List of strings, each representing a task.
     */
    public TaskList(List<String> taskStrings) {
        this();
        for (String taskStr : taskStrings) {
            this.tasks.add(Parser.fromStorage(taskStr));
        }
    }

    public List<String> toStorage() {
        return this.tasks.stream().map(Task::toStorage).collect(Collectors.toList());
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
