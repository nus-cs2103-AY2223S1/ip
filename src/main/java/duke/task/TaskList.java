package duke.task;

import duke.DukeException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    private static final String outOfBoundsMessage = "The entered task index is out of bounds";

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds and then returns a task object to the task list.
     *
     * @param task Task object to add to list.
     * @return The added task object.
     */
    public Task add(Task task) {
        this.taskList.add(task);
        return task;
    }

    /**
     * Deletes a task from the task list based on index.
     *
     * @param idx Task index to delete.
     * @return Deleted task.
     * @throws DukeException If idx not in range.
     */
    public Task delete(int idx) throws DukeException {
        try {
            return this.taskList.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    /**
     * Marks a task in the task list based on index.
     *
     * @param idx Task index to mark.
     * @return Marked task.
     * @throws DukeException If idx not in range.
     */
    public Task mark(int idx) throws DukeException {
        try {
            Task task = this.taskList.get(idx - 1);
            task.mark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    /**
     * Unmarks a task in the task list based on index.
     *
     * @param idx Task index to unmark.
     * @return Unmarked task.
     * @throws DukeException If idx not in range.
     */
    public Task unmark(int idx) throws DukeException {
        try {
            Task task = this.taskList.get(idx - 1);
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    /**
     * Returns a task in the task list based on index.
     *
     * @param idx Task index to get.
     * @return Task at idx.
     * @throws DukeException If idx not in range.
     */
    public Task get(int idx) throws DukeException {
        try {
            return this.taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    /**
     * Returns a filtered TaskList based on a query.
     *
     * @param query String to match in tasks.
     * @return TaskList with tasks where description contains query.
     */
    public TaskList filter(String query) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);
            if (t.getDescription().contains(query)) {
                result.add(t);
            }
        }
        return result;
    }

}
