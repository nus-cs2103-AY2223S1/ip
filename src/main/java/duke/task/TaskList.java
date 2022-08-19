package duke.task;


import duke.DukeException;
import duke.common.Messages;
import duke.ui.Ui;

import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    private final Ui ui;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }

    private Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTodo(String description) {
        Task task = new ToDo(description);
        tasks.add(task);
        ui.showMessages(
                Messages.MESSAGE_TASK_ADDED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));
    }

    public void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.showMessages(
                Messages.MESSAGE_TASK_ADDED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));
    }

    public void addEvent(String description, String at) {
        Task task = new Event(description, at);
        tasks.add(task);
        ui.showMessages(
                Messages.MESSAGE_TASK_ADDED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));
    }

    public void deleteTask(int inputIndex) throws DukeException {
        int targetIndex = checkIndex(inputIndex);
        Task task = tasks.get(targetIndex);
        tasks.remove(targetIndex);
        ui.showMessages(
                Messages.MESSAGE_TASK_DELETED,
                "  " + task,
                String.format(Messages.MESSAGE_TASK_NUMBER, tasks.size()));

    }

    public void markTask(int inputIndex) throws DukeException {
        updateTaskStatus(inputIndex, true);
    }

    public void unmarkTask(int inputIndex) throws DukeException {
        updateTaskStatus(inputIndex, false);
    }

    private void updateTaskStatus(int inputIndex, boolean isDone) throws DukeException {
        int targetIndex = checkIndex(inputIndex);
        tasks.get(targetIndex).setDone(isDone);
        ui.showMessages(
                String.format(Messages.MESSAGE_TASK_UPDATE_STATUS, isDone ? "done" : "not done"),
                "  " + getTask(targetIndex));
    }

    private int checkIndex(int inputIndex) throws DukeException {
        int actualListIndex = inputIndex - 1;
        if (actualListIndex >= tasks.size() || actualListIndex < 0) {
            throw new DukeException(Messages.MESSAGE_INVALID_NUMBER);
        }
        return actualListIndex;
    }

    public String encodeToString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.encodeToString()).append("\n");
        }
        return sb.toString();
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
