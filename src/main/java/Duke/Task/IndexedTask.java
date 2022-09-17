package Duke.Task;
/**
 * This class represents a wrapper for the task which includes
 * its index in the current task list.
 */
public class IndexedTask {

    /** The index of this task in the current list. */
    private int index;

    /** The actual task being indexed. */
    private Task task;

    /** Constructor for the indexed task. */
    public IndexedTask(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", this.index + 1, this.task.toString());
    }
}