package duke.task;

import java.util.ArrayList;

import duke.task.Task.Priority;
import duke.ui.Ui;

/**
 * Stores an `ArrayList&lt;Task&rt;` and handles the direct operations
 * on it.
 *
 * @author Kang Wei
 */
public class TaskList {

    private ArrayList<Task> tasks; // The list of all tasks of a user.

    /**
     * Initialises a TaskList object with an
     * `ArrayList&lt;Task&gt;` of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the currently stored
     * `ArrayList&lt;Task&gt;` of tasks.
     *
     * @param task      The task to store.
     * @param isVerbose If true, then outputs a message to the ui. False if otherwise.
     */
    public void addTask(Task task, boolean isVerbose) {
        tasks.add(task);
        if (isVerbose) {
            Ui.print("Hey sweetie, I've added: '" + task + "' to your lists of tasks~");
        }
    }

    /**
     * Deletes a task from the currently stored
     * `ArrayList&lt;Task&rt;` of tasks, by its index.
     *
     * @param index The index of the tasks to delete.
     */
    public void deleteTaskByIndex(int index) {
        int taskSizeBeforeDeletion = tasks.size();
        Task taskToRemove = tasks.get(index);
        tasks.remove(index);
        assert tasks.size() == taskSizeBeforeDeletion - 1: "Length of tasks should have dropped by 1";
        Ui.print(
                "I've successfully removed this task:\n"
                + taskToRemove
                + "\n\n"
                + "Do your own chores next time hunbun!"
        );
    }

    /**
     * Returns a list of all the user's tasks.
     *
     * @return The list of all tasks.
     */
    public String list() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += ((
                        i + 1)
                    + ". "
                    + tasks.get(i)
                    + "\n");
        }
        return output;
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to be marked.
     */
    public void mark(int index) {
        Task task = tasks.get(index);
    }

    /**
     * Unmarks a task, thus denoting it to be uncompleted.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmark(int index) {
        Task task = tasks.get(index);
    }

    /**
     * Returns the `ArrayList&lt;Task&rt;` stored in
     * this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of elements in this object's
     * stored tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Searches through its tasks whos descriptions fit the input String.
     *
     * @param searchString The String to be used to search the tasks.
     * @return A `TaskList&lt;Task&rt;` containing the tasks,
     */
    public TaskList searchUsingString(String searchString) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.taskDescription != null) {
                if (task.taskDescription.contains(searchString)) {
                    foundTasks.add(task);
                }
            } else if (task.miscDescription != null) {
                if (task.miscDescription.contains(searchString)) {
                    foundTasks.add(task);
                }
            }
        }

        return new TaskList(foundTasks);
    }

    /**
     * Sets the priority of a task to the given priority.
     *
     * @param index    The index of the task in `tasks`
     * @param priority The priority that the task should be set to.
     */
    public void setPriority(int index, Priority priority) {
        Task task = tasks.get(index);
        task.setPriority(priority);
    }
}
