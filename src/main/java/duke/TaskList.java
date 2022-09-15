package duke;

import java.util.ArrayList;
import java.util.HashSet;

import duke.exceptions.DukeDuplicateException;
import duke.exceptions.DukeMissingIndexException;



/**
 * Represents <code>TaskList</code> to hold <code>Task</code>
 */
public class TaskList {

    // Used to check for duplicate
    private static final HashSet<String> TASKSET = new HashSet<>();
    private static final ArrayList<Task> TASKLIST = new ArrayList<>();

    /**
     * Add a task into the taskList.
     *
     * @param task task to be added.
     */
    public static void add(Task task) throws DukeDuplicateException {
        String shortenedDescription = task.toString().substring(3);
        if (TASKSET.contains(shortenedDescription)) {
            throw new DukeDuplicateException();
        }
        TASKSET.add(shortenedDescription);
        System.out.println(TASKSET.size());
        TASKLIST.add(task);
    }

    /**
     * Prints out the list of tasks currently stored.
     */
    public static String read() {
        if (TASKLIST.size() == 0) {
            System.out.println("You have no task");
            return "You have no task";
        }

        StringBuilder ret = new StringBuilder();
        ret.append("Here are the tasks in your list: \n");
        for (int i = 0; i < TASKLIST.size(); i++) {
            Task task = TASKLIST.get(i);
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
        if (index >= TASKLIST.size() || TASKLIST.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        TASKLIST.get(index).setDone();
    }

    /**
     * Sets a task to be undone.
     *
     * @param index index of task to unmark.
     * @throws DukeMissingIndexException if no task with that index.
     */
    public static void unMark(int index) throws DukeMissingIndexException {
        if (index >= TASKLIST.size() || TASKLIST.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        TASKLIST.get(index).setNotDone();
    }

    /**
     * Deletes a task.
     *
     * @param index index of task to delete.
     * @throws DukeMissingIndexException if no task with that index.
     */
    public static void delete(int index) throws DukeMissingIndexException {
        if (index >= TASKLIST.size() || TASKLIST.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        Task taskToDelete = TASKLIST.get(index);
        String shortenedDescription = taskToDelete.toString().substring(3);
        TASKSET.remove(shortenedDescription);
        TASKLIST.remove(index);
    }

    /**
     * find and print all task with the prefix.
     *
     * @param prefix prefix of task to find.
     */
    public static String find(String prefix) {

        StringBuilder ret = new StringBuilder();
        ret.append("Here are the matching tasks in your list:");

        for (int i = 0; i < TASKLIST.size(); i++) {
            Task curr = TASKLIST.get(i);
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
        return TASKLIST;
    }

}
