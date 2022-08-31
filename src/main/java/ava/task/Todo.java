package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Todo" tasks.
 */
public class Todo extends Task {
    /**
     * The constructor for Todo task.
     *
     * @param description Task description.
     * @param isDone Task state.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks done a Todo task.
     *
     * @return Todo object.
     */
    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    /**
     * Marks undone a Todo task.
     *
     * @return Todo object.
     */
    @Override
    public Todo markUndone() {
        super.markUndone();
        return this;
    }

    /**
     * Changes the format of Task to write to the file.
     *
     * @return String format to write to file.
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " | " + this.description;
    }

    /**
     * Obtains a string representation of the task.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Execute process of given Todo task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this);
        storage.write(tasks.getTasks());
        return ui.showAddOnTask(tasks, (tasks.size() - 1));
    }
}
