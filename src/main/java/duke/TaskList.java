package duke;

import duke.exceptions.DukeMissingIndexException;

import java.util.ArrayList;

/**
 * Represents <code>TaskList</code> to hold <code>Task</code>
 */
public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();;

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
    public static void read() {
        if (taskList.size() == 0) {
            System.out.println("You have no task");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            if (curr != null) {
                System.out.println(i + 1 + "." + curr.toString());
            }
        }
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
     * Returns the arrayList itself.
     *
     * @return arrayList that store the tasks.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
