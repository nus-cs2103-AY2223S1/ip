package clevernotbot;

import task.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents history of what had done.
 */
public class History {
    //Change the number of undo you are able to do.
    private final int CACHE_SIZE = 10;
    private static LinkedList<TaskList> pastTaskLists = new LinkedList<>();

    /**
     * Returns if it can be undone.
     *
     * @return Boolean if it can be undone.
     */
    public boolean canUndo() {
        return pastTaskLists.size() > 0;
    }

    /**
     * Returns if it can be added.
     *
     * @return Boolean if it can be added.
     */
    private boolean canAdd() {
        return pastTaskLists.size() < CACHE_SIZE;
    }

    /**
     * Adds <code>TaskList</code> into <code>History</code>
     * if it can be added.
     *
     * @param taskList The tasklist that is added in.
     */
    public void addToHistoryList(TaskList taskList) {
        if (!canAdd()) {
            removeOldestHistory();
        }
        addInHistory(taskList);
    }

    /**
     * Returns the last tasklist from history.
     *
     * @param tasks Original TaskList.
     * @return The last tasklist or the original tasklist depending
     * on if it can be undone.
     */
    public TaskList retrieveLastHistory(TaskList tasks) {
        if (!canUndo()) {
            return tasks;
        } else {
            return pastTaskLists.removeLast();
        }
    }

    /**
     * Adds <code>TaskList</code> into <code>History</code>.
     *
     * @param taskList The tasklist that is added in.
     */
    private void addInHistory(TaskList taskList) {
        // A deep copy is required here.
        pastTaskLists.add(copyList(taskList));
    }

    /**
     * Removes the oldest tasklist in history.
     */
    private void removeOldestHistory() {
        pastTaskLists.removeFirst();
    }

    /**
     * Performs a deep copy of a list.
     *
     * @param tasks List of tasks
     * @return Duplicated task list.
     */
    public TaskList copyList(TaskList tasks) {
        ArrayList<Task> newTaskArray = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            Task newTask = deepCopyTask(task);
            newTaskArray.add(newTask);
        }
        return new TaskList(newTaskArray);
    }

    /**
     * Performs a deep copy of the task.
     *
     * @param task Task that is going to get copied.
     * @return The same task.
     */
    public Task deepCopyTask(Task task) {
        final String TODO_PREFIX = "T";
        final String DEADLINE_PREFIX = "D";
        switch (task.getTaskType()) {
        case TODO_PREFIX:
            return new ToDo(task.getName(), task.isCompleted());
        case DEADLINE_PREFIX:
            return new Deadline(task.getName(), task.isCompleted(), task.getTime());
        default:
            // Returns Event
            return new Event(task.getName(), task.isCompleted(), task.getTime());
        }
    }
}
