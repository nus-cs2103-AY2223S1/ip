package henry;

import java.util.List;

/**
 * TaskList represents the actual list to which tasks
 * can be added, removed or modified.
 */
public class TaskList {

    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes the task in the task list at the given index.
     *
     * @param index the index of the task to be deleted
     * @return the deleted task as a String
     */
    public String deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index).toString();
    }

    /**
     * Gets the task in the task list at the given index.
     *
     * @param task the index of the task to be retrieved
     * @return the task at the given index
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Gets the entire task list as a List of Tasks.
     *
     * @return the task list
     */
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a String representing the entire task list,
     * in simplified form that is used in file writing and
     * reading
     *
     * @return a String representing the entire task list.
     */
    public String toFileEncodedString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(tasks.get(i - 1).toSimpleString()).append("\n");
        }
        return sb.toString().trim();
    }
}
