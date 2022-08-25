package duke;

import java.util.List;

/**
 * Represents a list of tasks. A Tasklist object stores information about the tasks in the list.
 */
public class TaskList {
    private static int count = 0;
    private List<Task> list;
    private Ui ui;

    /**
     * Creates new TaskList object.
     *
     * @param list List containing all Task objects.
     */
    public TaskList(List<Task> list) {
        this.list = list;
        this.ui = new Ui();
        count += list.size();
    }

    /**
     * Marks task at particular index as done.
     *
     * @param index Index of task to be marked.
     */
    public void mark(int index) {
        Task task = this.list.get(index - 1);
        task.setDone(true);
        this.ui.showTaskMarked(task);
    }

    /**
     * Marks task at particular index as undone.
     *
     * @param index Index of task to be unmarked.
     */
    public void unmark(int index) {
        Task task = this.list.get(index - 1);
        task.setDone(false);
        this.ui.showTaskUnmarked(task);
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the end of the TaskList.
     *
     * @param task Task to bbe added.
     */
    public void add(Task task) {
        this.ui.showTaskAdded(task);
        this.list.add(task);
        count++;
        printTaskCount();
    }

    /**
     * Deletes task at a particular index of TaskList.
     *
     * @param index Index of task to be deleted.
     */
    public void delete(int index) {
        Task task = this.list.get(index - 1);
        this.ui.showTaskDeleted(task);
        this.list.remove(index - 1);
        count--;
        printTaskCount();
    }

    public void printTaskCount() {
        this.ui.showTaskCount(count);
    }

    /**
     * Returns String representation of TaskList object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        if (count == 0) {
            return "\tList is empty!";
        } else {
            String result = "\tHere are the tasks in your list:";
            for (int i = 0; i < count; i++) {
                result = result + "\n\t\t" + (i + 1) + "." + this.list.get(i);
            }
            return result;
        }
    }
}
