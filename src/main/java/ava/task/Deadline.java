package ava.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Deadline" tasks.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * The constructor for Deadline task.
     *
     * @param description Task description.
     * @param isDone Task state.
     * @param time Time of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Marks done a Deadline task.
     *
     * @return Deadline object.
     */
    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    /**
     * Marks undone a Deadline task.
     *
     * @return Deadline object.
     */
    @Override
    public Deadline markUndone() {
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
        return "D | " + mark + " | " + this.description + " | "
                + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Obtains a string representation of the task.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + " is due by "
                + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    /**
     * Executes process of given Deadline task.
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
