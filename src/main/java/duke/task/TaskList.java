package duke.task;

import java.util.ArrayList;

import duke.ui.Ui;

/**
 * Stores an <code>ArrayList<Task></code> and handles the direct operations 
 * on it.
 *
 * @author Kang Wei
 */
public class TaskList {

    ArrayList<Task> tasks; // The list of all tasks of a user.

    /**
     * Initialises a TaskList object with an
     * <code>ArrayList<Task></code> of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the currently stored
     * <code>ArrayList<Task></code> of tasks.
     *
     * @param task The task to store.
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
     * <code>ArrayList<Task></code> of tasks, by its index.
     *
     * @param index The index of the tasks to delete.
     */
    public void deleteTaskByIndex(int index) {
        Task taskToRemove = tasks.get(index);
        tasks.remove(index);
        Ui.print(
                "I've successfully removed this task:\n" +
                taskToRemove +
                "\n\n" +
                "Do your own chores next time hunbun!"
                );
    }

    /**
     * Sends a list of all of the user's tasks to the
     * ui.
     */
    public void list() {
        Ui.print("Sweetie, here is the list of tasks that you have <3");
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (
                    (i + 1)
                    + ". " 
                    + tasks.get(i)
                    + "\n");
        }
        Ui.print(output);
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to be marked.
     */
    public void mark(int index) {
        Task task = tasks.get(index);
        Ui.print(task.updateStatus(true));
    }

    /**
     * Unmarks a task, thus denoting it to be uncompleted.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmark(int index) {
        Task task = tasks.get(index);
        Ui.print(task.updateStatus(false));
    }

    /**
     * Returns the <code>ArrayList<Task></code> stored in
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
}
