import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this(new ArrayList<>());
    }

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task.
     * @param number the task number
     * @return true if the task was deleted, false otherwise
     */
    public boolean deleteTask(int number) {
        if (number > tasks.size()) {
            return false;
        }
        tasks.remove(number - 1);
        return true;
    }

    /**
     * Marks a task as completed.
     * @param number the task number
     * @return true if the task is modified, false otherwise
     */
    public boolean checkTask(int number) {
        if (number > tasks.size()) {
            return false;
        }
        return tasks.get(number - 1).setCompleted(true);
    }

    /**
     * Marks a task as incomplete.
     * @param number the task number
     * @return true if the task is modified, false otherwise
     */
    public boolean uncheckTask(int number) {
        if (number > tasks.size()) {
            return false;
        }
        return tasks.get(number - 1).setCompleted(false);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Your Tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
