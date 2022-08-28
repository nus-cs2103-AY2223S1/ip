package duke;

import java.util.ArrayList;

/**
 * Encapsulates the list of tasks inputted by the user.
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task specified by index from the TaskList.
     *
     * @param index
     * @return task object
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns size of the TaskList.
     *
     * @return size of the TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes a task from the TaskList, specified by the index.
     *
     * @param index
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns String representation of the TaskList.
     *
     * @return String representation of the TaskList
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            str += String.format("%d. %s \n", i + 1, this.tasks.get(i).toString());
        }
        return str.trim();
    }

    /**
     * Looks for tasks which contain the keyword, then passes the tasks to the Ui to be printed.
     *
     * @param keyword
     * @param ui
     */
    public void printMatchingTasks(String keyword, Ui ui) {
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getName().contains(keyword)) {
                ui.printTask(tasks.get(i));
            }
        }
    }
}

