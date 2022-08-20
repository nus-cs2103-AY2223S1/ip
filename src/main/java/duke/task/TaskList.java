package duke.task;


import java.util.List;

import duke.DukeException;
import duke.common.Messages;
import duke.ui.Ui;

/**
 * Represents the task list and methods to manage the task list.
 */
public class TaskList {

    private final List<Task> tasks;
    private final Ui ui;

    /**
     * Constructor for a new Task List.
     *
     * @param tasks list of tasks if preloaded
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }

    private Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Creates a new to-do and add it to the task list.
     *
     * @param description description of the to-do
     */
    public void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        ui.showMessages(
                Messages.MESSAGE_TASK_ADDED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));
    }

    /**
     * Creates a new deadline and add it to the task list.
     *
     * @param description description of the deadline
     * @param by date deadline is to be completed by
     */
    public void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.showMessages(
                Messages.MESSAGE_TASK_ADDED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));
    }

    /**
     * Creates a new event and add it to the task list.
     *
     * @param description description of the event
     * @param at time frame of the event
     */
    public void addEvent(String description, String at) {
        Task task = new Event(description, at);
        tasks.add(task);
        ui.showMessages(
                Messages.MESSAGE_TASK_ADDED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));
    }

    /**
     * Delete a task from the task list.
     *
     * @param inputIndex visible index of the task
     */
    public void deleteTask(int inputIndex) throws DukeException {
        int targetIndex = checkIndex(inputIndex);
        Task task = tasks.get(targetIndex);
        tasks.remove(targetIndex);
        ui.showMessages(
                Messages.MESSAGE_TASK_DELETED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));

    }

    /**
     * Mark a task of the task list as done.
     *
     * @param inputIndex visible index of the task
     */
    public void markTask(int inputIndex) throws DukeException {
        int targetIndex = checkIndex(inputIndex);
        tasks.get(targetIndex).mark();
        ui.showMessages(
                String.format(Messages.MESSAGE_TASK_UPDATE_STATUS, "done"),
                "  " + getTask(targetIndex));
    }

    /**
     * Unmark a task of the task list (not done).
     *
     * @param inputIndex visible index of the task
     */
    public void unmarkTask(int inputIndex) throws DukeException {
        int targetIndex = checkIndex(inputIndex);
        tasks.get(targetIndex).unmark();
        ui.showMessages(
                String.format(Messages.MESSAGE_TASK_UPDATE_STATUS, "not done"),
                "  " + getTask(targetIndex));
    }

    /**
     * Converts the list of tasks to a string to be saved.
     */
    public String encodeToString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.encodeToString()).append("\n");
        }
        return sb.toString();
    }

    private int checkIndex(int inputIndex) throws DukeException {
        int actualListIndex = inputIndex - 1;
        if (actualListIndex >= tasks.size() || actualListIndex < 0) {
            throw new DukeException(Messages.MESSAGE_INVALID_NUMBER);
        }
        return actualListIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("  %d.%s\n\t", i + 1, getTask(i)));
        }
        return sb.toString().stripTrailing();
    }

}
