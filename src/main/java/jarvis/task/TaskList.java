package jarvis.task;

import java.util.List;

/**
 * TaskList --- stores list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor.
     *
     * @param tasks list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets task by index.
     *
     * @param index index of task.
     * @return task at index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds task to list.
     *
     * @param task task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task by index.
     *
     * @param index index of task.
     * @return task to be removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns number of tasks.
     *
     * @return number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets list of tasks.
     *
     * @return list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t %d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                output.append("\n");
            }
        }
        if (tasks.size() == 0) {
            return "You do not have any tasks.";
        }
        return output.toString();
    }
}
