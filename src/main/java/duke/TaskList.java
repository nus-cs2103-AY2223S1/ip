package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the user's task list.
 */
public class TaskList {
    private ArrayList<Task> data;
    private Storage storage;
    private UI ui;

    public TaskList(Storage storage, UI ui) throws DukeException {
        this.data = storage.load();
        this.storage = storage;
        this.ui = ui;
    }
    
    public TaskList(ArrayList<Task> data) {
        this.data = data;
    }
    
    /**
     * Adds the task to the list and to the data file.
     *
     * @param task The task to be added.
     * @throws DukeException If data file cannot be accessed.
     */
    public String addTask(Task task) throws DukeException {
        data.add(task);
        storage.appendFile(task, data.size());
        return getAddTaskString(task);
    }

    /**
     * Gets the String response for Add task command.
     *
     * @param task Task that was added.
     * @return String representation of response.
     */
    private String getAddTaskString(Task task) {
        return "Teddy is on it! I've added this task:\n"
                + " " + task + "\n"
                + "Now you have " + data.size() + " tasks in the list. Woof!\n";
    }

    /**
     * Marks or unmarks the task at the specified pos in the list.
     *
     * @param pos The position of the task to be marked in the list.
     * @param isDone Indicates if the task is to be marked or unmarked.
     * @throws DukeException If data file cannot be accessed.
     */
    public String markTask(int pos, boolean isDone) throws DukeException {
        Task task = data.get(pos);
        task.mark(isDone);
        storage.updateFile(this);
        return getMarkTaskString(isDone, task);
    }

    /**
     * Gets the String response for Mark task command.
     *
     * @param isDone Indicates if the task was marked or unmarked.
     * @param task Task that was marked or unmarked.
     * @return String representation of response.
     */
    private String getMarkTaskString(boolean isDone, Task task) {
        return (isDone ? "YAY! I've marked this task as done:\n " : "Sure, I've marked this task as not done yet:\n ")
                + task + "\n";
    }

    /**
     * Removes the task from the list and from the data file.
     *
     * @param pos The position of the task to be removed in the list.
     * @throws DukeException If data file cannot be accessed.
     */
    public String deleteTask(int pos) throws DukeException {
        Task task = data.get(pos);
        data.remove(pos);
        storage.updateFile(this);
        return getDeleteTaskString(task);
    }

    /**
     * Gets the String response for Delete task command.
     *
     * @param task Task that was deleted.
     * @return String representation of response.
     */
    private String getDeleteTaskString(Task task) {
        return "Okay! I've removed this task:\n "
                + task + "\n"
                + "Now you have " + data.size() + " tasks in the list. Woof!\n";
    }

    /**
     * Finds all tasks in the list that contain the keyword specified.
     * It matches the keyword to task descriptions (excluding specifier information like /at /by).
     *
     * @return String representation of sorted list.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder("Teddy has found the matching tasks:\n");
        int counter = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).containsKeyword(keyword)) {
                sb.append(counter + 1).append(".").append(data.get(i).toString()).append("\n");
                counter++;
            }
        }
        return sb.toString();
    }

    /**
     * Sorts a copy of the list of tasks in chronological order of deadlines.
     * DeadlineTasks are placed above all other Tasks with no deadlines.
     * All other Tasks' relative positions to one another remain.
     * Original list of tasks is not modified, and subsequent commands like delete
     * or mark should be done in relation to the original list.
     *
     * @return String representation of sorted list.
     */
    public String sort() {
        ArrayList<Task> copy = new ArrayList<>(data);
        copy.sort(null);
        TaskList sortedTaskList = new TaskList(copy);
        return "I have sorted them in chronological order of deadlines!\n" + sortedTaskList;
    }

    /**
     * {@inheritDoc}
     * @return String representation of list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < data.size(); i++) {
            result.append(i + 1).append(".").append(data.get(i)).append("\n");
        }
        return result.toString();
    }
}
