package duke.task;

import java.util.ArrayList;
import java.util.Comparator;

import duke.DukeException;

/**
 * The TaskList class encapsulates the list of tasks and its associated methods.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initializes an instance of TaskList that is empty.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int getNumOfTask() {
        return taskList.size();
    }

    /**
     * Marks the n-th task of the TaskList.
     * If isDone is true, mark the task as done, otherwise mark as not done.
     *
     * @param n Index of task to-be-marked.
     * @param isDone Indicates whether task is done or not done.
     * @throws DukeException If n - 1 is outside the bounds of TaskList' size.
     */
    public void markTaskByIndex(int n, boolean isDone) throws DukeException {
        try {
            this.taskList.get(n - 1).isDoneSetter(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number you entered is not within the valid range.");
        }
    }

    /**
     * Deletes the n-th task in the TaskList.
     *
     * @param n Index of task to be deleted.
     * @throws DukeException If n - 1 is outside the bounds of TaskList's size.
     */
    public void deleteTaskByIndex(int n) throws DukeException {
        try {
            this.taskList.remove(n - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number you entered is not within the valid range.");
        }
    }

    /**
     * Returns the n-th task in the TaskList.
     *
     * @param n Index of task to be retrieved.
     * @return n-th task in TaskList.
     * @throws DukeException If n - 1 is outside the bounds of TaskList's size.
     */
    public Task getTaskByIndex(int n) throws DukeException {
        try {
            // start counting from 1
            return this.taskList.get(n - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number you entered is not within the valid range.");
        }
    }

    public TaskList getTaskListWithKeyword(String keyword) {
        TaskList listWithKeyword = new TaskList();
        for (Task task : this.taskList) {
            if (task.containKeyword(keyword)) {
                listWithKeyword.addTask(task);
            }
        }
        return listWithKeyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "List is empty";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }

    /**
     * Returns a string representation of the TaskList in a specific format for storage.
     *
     * @return String representation of the TaskList to store in a file.
     */
    public String toStorageString() {
        if (taskList.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(taskList.get(i).toStorageString()).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }

    /**
     * Sorts the current task list by date.
     *
     * Sort from earliest to latest when isAscending is true and vice versa.
     *
     * @param isAscending Boolean value to decide whether to sort ascending or descending.
     */
    public void sortByDate(boolean isAscending) {
        Comparator<Task> byDate = getDateComparator(isAscending);
        this.taskList.sort(byDate);
    }

    private Comparator<Task> getDateComparator(boolean isAscending) {
        return new Comparator<>() {
            @Override
            public int compare(Task task1, Task task2) {
                if (task1 instanceof ToDo) {
                    return 1;
                } else if (task2 instanceof ToDo) {
                    return -1;
                } else {
                    int res = handleTaskWithDate(task1, task2);
                    return isAscending ? res : res * -1;
                }
            }

            private int handleTaskWithDate(Task task1, Task task2) {
                int res = -2;
                try {
                    res = task1.getDate().isBefore(task2.getDate()) ? -1 : 1;
                } catch (DukeException e) {
                    assert false;
                }
                return res;
            }
        };
    }
}
