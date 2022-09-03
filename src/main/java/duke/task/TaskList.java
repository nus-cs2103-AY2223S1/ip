package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.ui.Message;

/**
 * Represents a task list that can store and update tasks.
 */
public class TaskList {
    /* ArrayList container to hold all the tasks. */
    private ArrayList<Task> taskList;

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
            throw new DukeException(Message.OUT_OF_BOUNDS_ERROR);
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
            throw new DukeException(Message.OUT_OF_BOUNDS_ERROR);
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
            throw new DukeException(Message.OUT_OF_BOUNDS_ERROR);
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
            throw new DukeException(Message.OUT_OF_BOUNDS_ERROR);
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

    /**
     * Returns a string with tasks on separate lines.
     *
     * @return String with tasks on separate lines.
     * @throws DukeException If task list is empty.
     */
    public String getListString() throws DukeException {
        String msg = "";
        if (this.getSize() == 0) {
            throw new DukeException(Message.EMPTY);
        }
        for (int i = 0; i < this.getSize(); i++) {
            msg += i + 1 + ". " + this.get(i);
            if (i < this.getSize() - 1) {
                msg += '\n';
            }
        }
        return msg;
    }

}
