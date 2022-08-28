package duke.task;

import duke.common.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task list
 *
 * @author Pontakorn Prasertsuk
 */
public class TaskList {

    private List<Task> list;

    /**
     * Constructs a new TaskList instance from the list of tasks
     *
     * @param list the list of tasks
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Constructs a new empty TaskList instance
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Returns the list of tasks
     *
     * @return the list of tasks
     */
    public List<Task> getTaskList() {
        return list;
    }

    /**
     * Adds a task to the list of tasks
     *
     * @param task the task to be added
     * @return task list after adding the task
     */
    public TaskList add(Task task) {
        list.add(task);
        return this;
    }

    /**
     * Deletes a task from the list of tasks
     *
     * @param index the index of the task to be deleted
     * @return task list after deleting the task
     * @throws DukeException if an error occurs
     */
    public TaskList delete(int index) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Task " + (index + 1) + " does not exist!");
        }
        list.remove(index);
        return this;
    }

    /**
     * Marks a task with status
     *
     * @param index  the index of the task to be marked
     * @param status the status of the task to be marked
     * @return the task list after marking the task
     * @throws DukeException if an error occurs
     */
    public TaskList mark(int index, boolean status) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Task " + (index + 1) + " does not exist!");
        }
        list.get(index).setStatus(status);
        return this;
    }

    public TaskList filter(String keyword) {
        List<Task> filtered = list.stream().filter(task -> task.title.contains(keyword))
                .collect(Collectors.<Task>toList());
        return new TaskList(filtered);
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < list.size(); i++) {
            ret += (i + 1) + ") " + list.get(i).toString() + "\n";
        }
        return ret;
    }
}
