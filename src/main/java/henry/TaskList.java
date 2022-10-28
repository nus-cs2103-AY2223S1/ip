package henry;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * TaskList represents the actual list to which tasks
 * can be added, removed or modified.
 */
public class TaskList implements Iterable<Task> {

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
     * Gets the size of the TaskList
     *
     * @return the number of Tasks in this TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the Task in the TaskList at the given index.
     *
     * @param index the index of the Task to be retrieved
     * @return the Task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Sets the Task in the TaskList at the given index to the given Task.
     *
     * @param index the index of the Task to be set
     * @param task  the Task to be set
     */
    public void set(int index, Task task) {
        tasks.set(index, task);
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
            sb.append(tasks.get(i - 1).toFileEncodedString()).append("\n");
        }

        return sb.toString().trim();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public Stream<Task> stream() {
        return tasks.stream();
    }
}
