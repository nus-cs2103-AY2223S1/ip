package zeus.main;

import java.util.ArrayList;
import java.util.Stack;

import zeus.task.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Stack<ArrayList<Task>> historicalTaskLists;

    /**
     * Constructs a Tasklist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.historicalTaskLists = new Stack<>();
    }

    /**
     * Constructs a TaskList with an input ArrayList of Tasks.
     *
     * @param tasks ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.historicalTaskLists = new Stack<>();
    }

    /**
     * Returns current size of task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task at the given index in the task list.
     *
     * @param idx Index of Task.
     * @return Task at the specific index in the task list.
     */
    public Task getTask(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        return this.tasks.get(idx);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task at given index from the task list.
     *
     * @param idx Index of Task to be removed.
     */
    public void removeTask(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        tasks.remove(idx);
    }

    /**
     * Sets the task at the given index as done.
     *
     * @param idx Index of target Task.
     */
    public void setTaskDone(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        this.tasks.get(idx).markAsDone();
    }

    /**
     * Sets the task at the given index as not done.
     *
     * @param idx Index of target Task.
     */
    public void setTaskNotDone(int idx) {
        assert (idx >= 0) && (idx < tasks.size());
        this.tasks.get(idx).markAsNotDone();
    }

    /**
     * Returns list of Tasks that match user's search input.
     *
     * @param stringToMatch User search input.
     * @return ArrayList of matching tasks.
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

    /**
     * Restores task list to most recent state.
     */
    public void undoLastVersion() {
        // restore current task list to previous version
        this.tasks = historicalTaskLists.pop();
    }

    /**
     * Adds a deep copy of current task list version to the stack of historical task lists.
     */
    public void saveCurrentTaskListVersion() {
        ArrayList<Task> deepCopyOfCurrentTaskList = new ArrayList<>();
        for (Task task : this.tasks) {
            deepCopyOfCurrentTaskList.add(task.copy());
        }
        this.historicalTaskLists.push(deepCopyOfCurrentTaskList);
    }

    /**
     * Returns size of stack containing historical versions of task lists.
     *
     * @return Number of task lists in stack.
     */
    public int getHistorySize() {
        return historicalTaskLists.size();
    }
}
