package duke;

import java.util.ArrayList;

/**
 * Represents a TaskList. A <code>TaskList</code> is an <code>ArrayList</code> that contains <code>Tasks</code>.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Initialises TaskList object. Creates a new ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns size of TaskList.
     * @return size of TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Add task into TaskList.
     * @param task Task to be added into TaskList.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Delete task from TaskList.
     * @param i index (from Ui) of task to mark as done
     * @return Deletes task of index i - 1 from TaskList.
     */
    public Task delete(int i) {
        Task toDelete = taskList.remove(i - 1);
        return toDelete;
    }

    /**
     * Mark task from TaskList as done.
     * @param i index (from Ui) of task to mark as done
     * @return Marks task of index i - 1 as done.
     */
    public Task mark(int i) {
        Task toMark = taskList.get(i - 1);
        toMark.markAsDone();
        return toMark;
    }

    /**
     * Mark task from TaskList as undone.
     * @param i index (from Ui) of task to mark as undone
     * @return Marks task of index i - 1 as undone.
     */
    public Task unmark(int i) {
        Task toUnmark = taskList.get(i - 1);
        toUnmark.markAsUndone();
        return toUnmark;
    }

    /**
     * Returns task from TaskList.
     * @param i index of task in TaskList.
     * @return Task i of TaskList.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Prints the String representation of the TaskList.
     */
    public void print() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }
}
