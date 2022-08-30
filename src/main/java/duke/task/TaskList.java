package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * List data structure to manage tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor for {@code TaskList} with a given list of tasks.
     *
     * @param tasks {@code List} object that stores {@code Task} objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for {@code TaskList} with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the {@code TaskList}.
     *
     * @return Number of tasks in the {@code TaskList}.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Default display message for task index out of range.
     *
     * @return {@code String} message declaring that the task index is out of range.
     */
    String getIndexOutOfBoundsExceptionMessage() {
        return this.size() > 0
                ? String.format("Task index out of range. Please choose from index 1 to %d", this.size())
                : "Tasks index out of range. There are no tasks.";
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index Index of the {@code Task} in the {@code TaskList} to get.
     * @return {@code Task} at the specified index.
     * @throws DukeException {@code IndexOutOfBoundsException} caught.
     */
    Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(this.getIndexOutOfBoundsExceptionMessage());
        }
    }

    /**
     * Adds a {@code Task} object to the {@code TaskList}.
     *
     * @param task {@code Task} to add.
     * @return {@code Task} added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Removes the {@code Task} object at the specified index from the {@code TaskList}.
     *
     * @param index Index of {@code Task} to delete.
     * @return {@code Task} deleted.
     * @throws DukeException Checked exceptions that may occur when deleting a task.
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(this.getIndexOutOfBoundsExceptionMessage());
        }
    }

    /**
     * Marks a {@code Task} object at the specified index as done.
     *
     * @param index Index of {@code Task} to mark.
     * @return Updated {@code Task}.
     * @throws DukeException Checked exceptions that may occur when marking a task.
     */
    public Task markTask(int index) throws DukeException {
        this.getTask(index).markAsDone();
        return this.getTask(index);
    }

    /**
     * Marks a {@code Task} object at the specified index as not done.
     *
     * @param index Index of {@code Task} to unmark.
     * @return Updated {@code Task}.
     * @throws DukeException Checked exceptions that may occur when unmarking a task.
     */
    public Task unmarkTask(int index) throws DukeException {
        this.getTask(index).unmarkAsDone();
        return this.getTask(index);
    }

    /**
     * Returns a {@code List} of String representations of all tasks in {@code TaskList} in display format.
     *
     * @return {@code List} of String representation of all tasks in display format.
     */
    public List<String> getAllTasksInDisplayFormat() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
        return numberedTaskList;
    }

    /**
     * Returns a {@code List} of String representations of all tasks in {@code TaskList} in storage format.
     *
     * @return {@code List} of String representation of all tasks in storage format.
     */
    public List<String> getAllTasksInStorageFormat() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(tasks.get(i).getStorageFormat());
        }
        return numberedTaskList;
    }

    /**
     * Returns a {@code TaskList} of all tasks that contains the search keyword in their description.
     *
     * @return {@code TaskList} of all tasks containing the search keyword in their description.
     */
    public TaskList find(String keyword) {
        TaskList tasksMatched = new TaskList();
        for (Task task : this.tasks) {
            if (task.contains(keyword)) {
                tasksMatched.addTask(task);
            }
        }
        return tasksMatched;
    }

    /**
     * Returns a String representation of {@code TaskList} in display format.
     */
    @Override
    public String toString() {
        return String.join(System.lineSeparator(), getAllTasksInDisplayFormat());
    }
}
