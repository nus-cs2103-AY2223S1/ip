package tasklist;


import java.util.ArrayList;

import task.Task;


/**
 * Manage all interactions between Duke and UserInputHistory FILE storage.
 */
public class TaskList<T extends Task> {
    private final ArrayList<T> USER_INPUT_HISTORY_LIST = new ArrayList<>();

    /**
     * Adds Task to list.
     *
     * @param t Task to add.
     */
    public void addTask(T t) {
        USER_INPUT_HISTORY_LIST.add(t);
    }

    /**
     * Adds Event to list.
     *
     * @param e Event to add.
     */
    public void addEvent(T e) {
        USER_INPUT_HISTORY_LIST.add(e);
    }

    /**
     * Adds Deadline to list.
     *
     * @param d Deadline to add.
     */
    public void addDeadline(T d) {
        USER_INPUT_HISTORY_LIST.add(d);
    }

    /**
     * Deletes task at index (n - 1) in the list.
     *
     * @param n Index to remove.
     */
    public void deleteTask(int n) {
        USER_INPUT_HISTORY_LIST.remove(n - 1);
    }

    /**
     * Returns true if task at index (n - 1) has date today.
     *
     * @param n Index of task to check date.
     * @return True if task due today.
     */
    public boolean checkIsToday(int n) {
        return USER_INPUT_HISTORY_LIST.get(n - 1).isToday();
    }

    /**
     * Returns long description of task at index (n - 1) in the list.
     *
     * @param n Index of task to get long description of.
     * @return Long description of the task.
     */
    public String getLongDescription(int n) {
        return USER_INPUT_HISTORY_LIST.get(n - 1).longDescription();
    }

    /**
     * Returns size of the list.
     *
     * @return Size of the list.
     */
    public int getSize() {
        return USER_INPUT_HISTORY_LIST.size();
    }

    /**
     * Returns a mutable formatting of all
     * tasks present in the list.
     *
     * @return Mutable string containing details of all tasks.
     */
    public StringBuffer getContents() {
        StringBuffer list = new StringBuffer();
        for (int i = 0; i < USER_INPUT_HISTORY_LIST.size(); i++) {
            list.append((i + 1) + ". " + USER_INPUT_HISTORY_LIST.get(i) + "\n");
        }
        return list;
    }

    /**
     * Marks completed task at index (n - 1).
     *
     * @param n Index to mark.
     */
    public void markTask(int n) {
        USER_INPUT_HISTORY_LIST.get(n - 1).markAsDone();
    }

    /**
     * Unmarks completed task at index (n - 1).
     *
     * @param n Index to unmark.
     */
    public void unmarkTask(int n) {
        USER_INPUT_HISTORY_LIST.get(n - 1).markAsNotDone();
    }

    /**
     * Returns task at index (n - 1).
     *
     * @param n Index to return.
     * @return Task/Event/Deadline.
     */
    public T getTask(int n) {
        return USER_INPUT_HISTORY_LIST.get(n - 1);
    }

    /**
     * Returns new TaskList containing
     * all Tasks whose description
     * includes keyword given.
     *
     * @param keyword Keyword to look for in tasks.
     * @return TaskList of all Tasks found.
     */
    public TaskList findTasks(String keyword) {
        TaskList<T> matchingTasks = new TaskList<>();
        T currTask;
        String description;
        boolean matches;
        for (int i = 0; i < USER_INPUT_HISTORY_LIST.size(); i++) {
            currTask = USER_INPUT_HISTORY_LIST.get(i);
            description = currTask.getDescription().toLowerCase();
            matches = description.contains(keyword.toLowerCase());
            if (matches) {
                matchingTasks.USER_INPUT_HISTORY_LIST.add(currTask);
            }
        }
        return matchingTasks;
    }
}
