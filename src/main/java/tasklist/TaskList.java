package tasklist;

import java.util.ArrayList;

import task.Task;
/**
 * Manage all interactions between Duke and UserInputHistory FILE storage.
 */
public class TaskList {
    private final ArrayList<Task> userInputHistoryList = new ArrayList<>();

    /**
     * Adds Task to list.
     *
     * @param t Task to add.
     */
    public void addTask(Task t) {
        userInputHistoryList.add(t);
    }

    /**
     * Deletes task at index (n - 1) in the list.
     *
     * @param n Index to remove.
     */
    public void deleteTask(int n) {
        userInputHistoryList.remove(n - 1);
    }

    /**
     * Returns true if task at index (n - 1) has date today.
     *
     * @param n Index of task to check date.
     * @return True if task due today.
     */
    public boolean checkIsToday(int n) {
        return userInputHistoryList.get(n - 1).isToday();
    }

    /**
     * Returns long description of task at index (n - 1) in the list.
     *
     * @param n Index of task to get long description of.
     * @return Long description of the task.
     */
    public String getLongDescription(int n) {
        return userInputHistoryList.get(n - 1).longDescription();
    }

    /**
     * Returns size of the list.
     *
     * @return Size of the list.
     */
    public int getSize() {
        return userInputHistoryList.size();
    }

    /**
     * Returns a mutable formatting of all
     * tasks present in the list.
     *
     * @return Mutable string containing details of all tasks.
     */
    public StringBuffer getContents() {
        StringBuffer list = new StringBuffer();
        for (int i = 0; i < userInputHistoryList.size(); i++) {
            list.append((i + 1) + ". " + userInputHistoryList.get(i) + "\n");
        }
        return list;
    }

    /**
     * Marks completed task at index (n - 1).
     *
     * @param n Index to mark.
     */
    public void markTask(int n) {
        userInputHistoryList.get(n - 1).markAsDone();
    }

    /**
     * Unmarks completed task at index (n - 1).
     *
     * @param n Index to unmark.
     */
    public void unmarkTask(int n) {
        userInputHistoryList.get(n - 1).markAsNotDone();
    }

    /**
     * Returns task at index (n - 1).
     *
     * @param n Index to return.
     * @return Task/Event/Deadline.
     */
    public Task getTask(int n) {
        return userInputHistoryList.get(n - 1);
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
        TaskList matchingTasks = new TaskList();
        findAllMatchingTasks(keyword, userInputHistoryList, );
        return matchingTasks;
    }

    private TaskList findAllMatchingTasks(String keyword, ArrayList<Task> history, TaskList matchingTasks) {
        userInputHistoryList.stream().filter(currTask -> {
            String description = currTask.getDescription().toLowerCase();
            boolean isMatched = description.contains(keyword.toLowerCase());
            return isMatched;
        }).forEach(currTask -> {
            matchingTasks.addTask(currTask);
        });
        return matchingTasks;
    }
}
