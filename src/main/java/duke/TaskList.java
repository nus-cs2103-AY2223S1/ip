package duke;

import java.util.ArrayList;

import duke.exceptions.DukeMissingIndexException;



/**
 * Represents <code>TaskList</code> to hold <code>Task</code>
 */
public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Add a task into the taskList.
     *
     * @param task task to be added.
     */
    public static void add(Task task) {
        taskList.add(task);
    }

    /**
     * Prints out the list of tasks currently stored.
     */
    public static String read() {
        if (taskList.size() == 0) {
            System.out.println("You have no task");
            return "You have no task";
        }

        StringBuilder ret = new StringBuilder();
        ret.append("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            ret.append(i + 1).append(". ").append(task).append("\n");
        }

        return ret.toString();
    }

    /**
     * Sets a task to be done.
     *
     * @param index index of task to mark.
     * @throws DukeMissingIndexException if no task with that index.
     */
    public static void mark(int index) throws DukeMissingIndexException {
        if (index >= taskList.size() || taskList.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        taskList.get(index).setDone();
    }

    /**
     * Sets a task to be undone.
     *
     * @param index index of task to unmark.
     * @throws DukeMissingIndexException if no task with that index.
     */
    public static void unMark(int index) throws DukeMissingIndexException {
        if (index >= taskList.size() || taskList.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        taskList.get(index).setNotDone();
    }

    /**
     * Deletes a task.
     *
     * @param index index of task to delete.
     * @throws DukeMissingIndexException if no task with that index.
     */
    public static void delete(int index) throws DukeMissingIndexException {
        if (index >= taskList.size() || taskList.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        taskList.remove(index);
    }

    /**
     * find and print all task with the prefix.
     *
     * @param prefix prefix of task to find.
     */
    public static String find(String prefix) {

        StringBuilder ret = new StringBuilder();
        ret.append("Here are the matching tasks in your list:");

        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            if (curr == null) {
                continue;
            }

            if (curr.startsWith(prefix)) {
                ret.append(i + 1).append(". ").append(curr);
            }
        }
        return ret.toString();
    }

    /**
     * Returns the arrayList itself.
     *
     * @return arrayList that store the tasks.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
