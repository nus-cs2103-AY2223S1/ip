package ava.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Events" tasks.
 */
public class Event extends Task {
    protected LocalDateTime time;

    /**
     * The constructor for Event task.
     *
     * @param description Task description.
     * @param isDone Task state.
     */
    public Event(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Marks done an Event task.
     *
     * @return Event object.
     */
    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    /**
     * Marks undone an Event task.
     *
     * @return Event object.
     */
    @Override
    public Event markUndone() {
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
        return "E | " + mark + " | " + this.description + " | "
                + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Overridden toString method for Event task details
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")) + ")";
    }

    /**
     * Executes process of given Event task.
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this);
        ui.showAddOnTask(tasks, (tasks.size() - 1));
        storage.write(tasks.getTasks());
    }
}
