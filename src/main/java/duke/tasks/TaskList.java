package duke.tasks;

import java.util.ArrayList;

/**
 * TaskList keeps track of all tasks from user
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns filtered task list based on keyword
     *
     * @param text Keyword to be found
     * @return Filtered task list based on keyword
     */
    public ArrayList<Task> find(String text) {
        ArrayList<Task> filtered = new ArrayList<>(this.tasks);
        filtered.removeIf(task -> !task.getDescription().contains(text));
        return filtered;
    }
}
