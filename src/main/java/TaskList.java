import java.util.ArrayList;

/**
 * Class encapsulating task list and operations on task list.
 */
public class TaskList {
    /* List of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructor of TaskList class.
     *
     * @param tasks ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds Task into task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves Task from task list given index of task as a string.
     * Throws DukeException if given index is invalid.
     *
     * @param indexString String containing index of task to be retrieved.
     * @return Task corresponding to given index in task list.
     * @throws DukeException If given index is invalid.
     */
    public Task getTask(String indexString) throws DukeException {
        try {
            int index = Integer.parseInt(indexString); // Throw NFE if invalid int
            Task task = this.tasks.get(index - 1); // Throws IOOBE if invalid index
            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("\tSorry, that Task Number doesn't look right...");
        }
    }

    /**
     * Outputs all tasks in task list.
     */
    public void displayTaskList() {
        if (this.tasks.size() == 0) {
            System.out.print("\tLooks like you don't have any tasks for now!");
            return;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);

            // Reach end of list
            if (currTask == null) {
                break;
            }

            // Output string
            String taskListString = String.format("\t%d. %s", i, currTask);
            System.out.println(taskListString);
        }
    }

    /**
     * Deletes task at given index from task list.
     *
     * @param indexString String input containing index of task to be deleted.
     */
    public void deleteTask(String indexString) throws DukeException {
        try {
            Task task = getTask(indexString);
            this.tasks.remove(task);

        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Sets task status of task at given index of task list as completed or not completed.
     *
     * @param indexString String input containing index of task to be marked.
     * @param keyword     Keyword value to determine whether to mark or unmark the task.
     */
    public void markUnmarkTask(String indexString, Keyword keyword) throws DukeException {
        try {
            Task task = this.getTask(indexString);

            switch (keyword) {
            case MARK: {
                task.markAsDone();
                break;
            }
            case UNMARK: {
                task.markAsNotdone();
                break;
            }
            }
        } catch (DukeException de) {
            throw de;
        }

    }

    /**
     * Returns number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int size() {
        return this.tasks.size();
    }
}
