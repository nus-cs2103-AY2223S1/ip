package duke;

import java.util.ArrayList;
import java.util.Comparator;

import duke.task.Task;

/**
 * TaskList is the list that contains the tasks.
 */
public class TaskList {
    private static final int INITIAL_SIZE = 0;
    private static final int MAX_TASKS = 100;
    private static final Comparator<Task> CHRONOLOGICAL = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
    private static final Comparator<Task> REVERSE_CHRONOLOGICAL = (o1, o2) -> o2.getDate().compareTo(o1.getDate());
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Initializes a TaskList object, if no past data exists.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(MAX_TASKS);
        this.size = INITIAL_SIZE;
    }

    /**
     * Initializes a TaskList object, if past data exists.
     *
     * @param tasks The ArrayList that contains saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(MAX_TASKS);
        this.tasks.addAll(tasks);
        this.size = tasks.size();
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int getSize() {
        assert size >= 0 : "size should be greater than or equal to 0";
        return this.size;
    }

    /**
     * Adds a task to the current TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(this.tasks.size(), task);
        this.size++;
    }

    /**
     * Returns task of index i from TaskList.
     *
     * @param i The index of the task.
     * @return The task corresponding to the index.
     */
    public Task get(int i) {
        assert i >= 0 : "i should be greater than or equal to 0";
        return this.tasks.get(i);
    }

    /**
     * Deletes task of index i from TaskList.
     *
     * @param i The index of the task.
     * @return The deleted task.
     */
    public Task delete(int i) {
        assert i >= 0 : "i should be greater than or equal to 0";
        Task deleted = tasks.remove(i);
        this.size--;
        return deleted;
    }

    /**
     * Sorts the tasks in the list by its date in chronological order.
     */
    public void sortChrono() {
        this.tasks.sort(CHRONOLOGICAL);
    }

    /**
     * Sorts the tasks in the list by its date in reverse chronological order.
     */
    public void sortReverseChrono() {
        this.tasks.sort(REVERSE_CHRONOLOGICAL);
    }

}
