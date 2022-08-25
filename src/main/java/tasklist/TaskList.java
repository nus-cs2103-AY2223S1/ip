package tasklist;


import java.util.ArrayList;

import task.Task;


/**
 * Manage all interactions between Duke and UserInputHistory FILE storage.
 */
public class TaskList<T extends Task> {
    private ArrayList<T> userInputHistory = new ArrayList<>();

    /**
     * Adds Task to list.
     * @param t Task to add.
     */
    public void addTask(T t) {
        userInputHistory.add(t);
    }

    /**
     * Adds Event to list.
     * @param e Event to add.
     */
    public void addEvent(T e) {
        userInputHistory.add(e);
    }

    /**
     * Adds Deadline to list.
     * @param d Deadline to add.
     */
    public void addDeadline(T d) {
        userInputHistory.add(d);
    }

    /**
     * Deletes task at index (n - 1) in the list.
     * @param n Index to remove.
     */
    public void deleteTask(int n) {
        userInputHistory.remove(n - 1);
    }

    /**
     * Returns true if task at index (n - 1) has date today.
     * @param n Index of task to check date.
     * @return True if task due today.
     */
    public boolean checkIsToday(int n) {
        return userInputHistory.get(n - 1).isToday();
    }

    /**
     * Returns long description of task at index (n - 1) in the list.
     * @param n Index of task to get long description of.
     * @return Long description of the task.
     */
    public String getLongDescription(int n) {
        return userInputHistory.get(n - 1).longDescription();
    }

    /**
     * Returns size of the list.
     * @return Size of the list.
     */
    public int getSize() {
        return userInputHistory.size();
    }

    /**
     * Returns a mutable formatting of all
     * tasks present in the list.
     * @return Mutable string containing details of all tasks.
     */
    public StringBuffer getContents() {
        StringBuffer list = new StringBuffer();
        for (int i = 0; i < userInputHistory.size(); i++) {
            list.append((i + 1) + ". " + userInputHistory.get(i) + "\n");
        }
        return list;
    }

    /**
     * Marks completed task at index (n - 1)
     * @param n Index to mark.
     */
    public void markTask(int n) {
        userInputHistory.get(n - 1).markAsDone();
    }

    /**
     * Unmarks completed task at index (n - 1)
     * @param n Index to unmark.
     */
    public void unmarkTask(int n) {
        userInputHistory.get(n - 1).markAsNotDone();
    }

    /**
     * Returns task at index (n - 1).
     * @param n Index to return.
     * @return Task/Event/Deadline.
     */
    public T getTask(int n) {
        return userInputHistory.get(n - 1);
    }

    /**
     * Returns new TaskList containing
     * all Tasks whose description
     * includes keyword given.
     * @param keyword Keyword to look for in tasks.
     * @return TaskList of all Tasks found.
     */
    public TaskList findTasks(String keyword) {
        TaskList<T> matchingTasks = new TaskList<>();
        T currTask;
        String description;
        boolean matches;
        for (int i = 0; i < userInputHistory.size(); i++) {
            currTask = userInputHistory.get(i);
            description = currTask.getDescription().toLowerCase();
            matches = description.contains(keyword.toLowerCase());
            if (matches) {
                matchingTasks.userInputHistory.add(currTask);
            }
        }
        return matchingTasks;
    }
}
