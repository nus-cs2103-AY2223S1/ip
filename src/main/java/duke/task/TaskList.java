package duke.task;

import java.util.List;

import duke.Ui;
import duke.exception.DukeOutOfBoundException;
/**
 * A TaskList class that stores the list of Tasks.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class TaskList {

    private List<Task> taskList;

    private Ui ui;

    /**
     * Constructor for TaskList.
     *
     * @param taskList List to store the tasks.
     * @param ui Ui that deals with interaction with users.
     */
    public TaskList(List<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Adds task into TaskList.
     *
     * @param task task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
        ui.addMessage(this.taskList.size(), task);
    }

    /**
     * Deletes task from TaskList.
     *
     * @param index index of task to be deleted in TaskList.
     * @throws DukeOutOfBoundException Exception thrown when index is out of size of TaskList.
     */
    public void delete(int index) throws DukeOutOfBoundException {
        if (index >= taskList.size()) {
            throw new DukeOutOfBoundException();
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.deleteMessage(this.taskList.size(), task);
    }

    /**
     * Marks Task as complete.
     *
     * @param index Index of task to be marked as complete
     * @throws DukeOutOfBoundException Exception thrown when index is out of size of TaskList.
     */
    public void mark(int index) throws DukeOutOfBoundException {
        if (index >= taskList.size()) {
            throw new DukeOutOfBoundException();
        }
        Task task = taskList.get(index);
        task.markAsDone();
        ui.markMessage(task);
    }

    /**
     * Unmark task as complete.
     *
     * @param index index of task to be unmarked.
     * @throws DukeOutOfBoundException Exception thrown when index is out of size of TaskList.
     */
    public void unmark(int index) throws DukeOutOfBoundException {
        if (index >= taskList.size()) {
            throw new DukeOutOfBoundException();
        }
        Task task = taskList.get(index);
        task.markAsUndone();
        ui.unmarkMessage(task);
    }

    /**
     * Getter for TaskList.
     *
     * @return The TaskList.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }
}
