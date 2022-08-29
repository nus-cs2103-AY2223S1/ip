package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Storage storage;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Returns task at the given index from the list.
     *
     * @param index Index of the task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes task at the given index from the list.
     *
     * @param index Index of the task.
     */
    public void remove(int index) {
        tasks.remove(index);
        this.storage.update(tasks);
    }

    /**
     * Adds task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        this.storage.update(tasks);
    }

    /**
     * Returns the number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Index of the task.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
        this.storage.update(tasks);
    }

    /**
     * Marks the task at the given index as undone.
     *
     * @param index Index of the task.
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
        this.storage.update(tasks);
    }

    /**
     * Returns a list of tasks which contain the given input in their descriptions.
     *
     * @param input The String to search for.
     * @return String containing a numbered list of the results.
     */
    public String find(String input) {
        StringBuilder str = new StringBuilder();
        ArrayList<Task> results = new ArrayList<>();

        for (Task task : tasks) {
            if (task.contains(input)) {
                results.add(task);
            }
        }

        for (int i = 0; i < results.size(); i++) {
            str.append("\n" + (i + 1) + ". " + results.get(i).toString());
        }
        return str.toString();
    }

    /**
     * Returns the details of the task at the given index.
     *
     * @param index Index of the task.
     * @return Details of the task.
     */
    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Returns the numbered list of tasks stored.
     *
     * @return List of tasks.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return str.toString();
    }
}
