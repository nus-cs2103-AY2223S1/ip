package duke.manager;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents the list of current tasks.
 */
public class TaskList {
    /** The user's current tasks. */
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> storedTasks) {
        this.list = storedTasks;
    }

    /**
     * Returns the number of current tasks.
     *
     * @return The number of current tasks.
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Adds the given task into the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes the given task from the list of tasks.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return The string representation of the deleted task.
     * @throws DukeException If the task number is not found in the list.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        Task task;
        try {
            task = this.list.get(taskIndex);
            this.list.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Task number doesn't exist!");
        }
        return task.toString();
    }

    /**
     * Marks the given task as completed or uncompleted.
     *
     * @param taskIndex The index of the task to be marked.
     * @param done Whether the task is completed.
     * @return The string representation of the marked task.
     * @throws DukeException If the task number is not found in the list.
     */
    public String markTask(int taskIndex, boolean done) throws DukeException {
        Task task;
        try {
            task = this.list.get(taskIndex);
            task.markTask(done);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Task number doesn't exist!");
        }
        return task.toString();
    }

    /**
     * Returns the list of tasks that contain the given keyword.
     *
     * @param keyword The given string keyword.
     * @return The list of tasks containing the given keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (task.doesContainKeyword(keyword)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }

    /**
     * Returns the string representation of the tasks to be saved.
     *
     * @return The string representation of the tasks to be saved.
     */
    public String getTasksString() {
        String tasksString = "";
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            String taskItem = task.getTask() + "\n";
            tasksString += taskItem;
        }
        return tasksString;
    }

    @Override
    public String toString() {
        //todo: use string builder
        if (list.isEmpty()) {
            return "You haven't added anything to your list!";
        }

        String taskListString = "";

        for (int index = 1; index <= list.size(); index++) {
            Task listItem = list.get(index - 1);
            String listItemString = index + ". " + listItem.toString();
            if (index != list.size()) {
                listItemString += "\n";
            }
            taskListString += listItemString;
        }

        return taskListString;
    }
}
