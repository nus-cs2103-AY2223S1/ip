import java.util.List;
import java.util.ArrayList;

/**
 * Stores a list of tasks entered by the user.
 */
public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the task at the specific position as done.
     *
     * @param pos The position of the task in the task list.
     * @return The task at the specified position.
     */
    public Task mark(int pos) {
        Task task = this.tasks.get(pos);
        task.markDone();
        return task;
    }

    /**
     * Marks the task at the specific position as not done.
     *
     * @param pos The position of the task in the task list.
     * @return The task at the specified position.
     */
    public Task unmark(int pos) {
        Task task = this.tasks.get(pos);
        task.markUndone();
        return task;
    }

    /**
     * Returns the String representation of all tasks in the list.
     *
     * @return A String representing all tasks in the list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            int taskNum = i + 1;
            result += taskNum + "." + task;
            if (i != this.tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
