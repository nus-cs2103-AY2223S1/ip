package duke.task;

import java.util.ArrayList;

import duke.errors.DukeException;

/**
 * Represents tasks in a list
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for no tasks
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor for existing tasks
     * @param tasks to be added
     */
    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    /**
     * @return size of task list
     */
    public int size() {
        return list.size();
    }

    /**
     * @param task adds task to task list
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes tasks
     * @param index of task to be deleted
     * @return task deleted
     * @throws DukeException thrown when list is out of bounds
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Delete task list failed, check index boundary");
        }
    }

    /**
     * Gets required task
     * @param index of task required
     * @return task obtained
     * @throws DukeException thrown when list is out of bounds
     */
    public Task get(int index) throws DukeException {
        try {
            return list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Get task list failed, check index boundary");
        }

    }

    /**
     * Marks the task in task list
     * @param index of task to be marked
     * @throws DukeException thrown when list is out of bounds
     */
    public void mark(int index) throws DukeException {
        try {
            list.get(index - 1).finished();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("mark failed");
        }
    }

    /**
     * Unmarks the task in task list
     * @param index of task to be unmarked
     * @throws DukeException thrown when list is out of bounds
     */
    public void unmark(int index) throws DukeException {
        try {
            list.get(index - 1).notFinished();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("unmark failed");
        }
    }

    /**
     * Finds task in task list
     * @param keyword in description
     * @return Array list of tasks found
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(list.get(i));
            }
        }
        return matchingTasks;
    }
}
