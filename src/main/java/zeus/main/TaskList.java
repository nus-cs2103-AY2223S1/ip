package zeus.main;

import java.util.ArrayList;

import zeus.task.Task;


/**
 * Class that represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor of Tasklist class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns current size of task list.
     *
     * @return size of task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Getter that returns the Task at the given index in the task list.
     *
     * @param idx index of Task
     * @return Task at the specific index in the task list
     */
    public Task getTask(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        return this.tasks.get(idx);
    }

    /**
     * Getter that returns the list of tasks.
     *
     * @return an ArrayList containing the tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to task list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task at given index from the task list.
     *
     * @param idx Index of Task to be removed
     */
    public void removeTask(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        tasks.remove(idx);
    }

    /**
     * Sets the task at the given index as done.
     * @param idx Index of target Task
     */
    public void setTaskDone(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        this.tasks.get(idx).markAsDone();
    }

    /**
     * Sets the task at the given index as not done.
     *
     * @param idx index of target Task
     */
    public void setTaskNotDone(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        this.tasks.get(idx).markAsNotDone();
    }

    /**
     * Returns list of Tasks that match user's search input.
     *
     * @param stringToMatch User search input
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> findMatchingTasks(String stringToMatch) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(stringToMatch)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
