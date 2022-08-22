package Duke.Task;

import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent "Events" tasks.
 */
public class Event extends Task {
    protected LocalDateTime time;

    /**
     * The constructor for Duke.Task.Event task
     * @param description
     * @param isDone
     */
    public Event(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * the method to mark as done the Duke.Task.Event task
     * @return Duke.Task.Event object
     */
    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    /**
     * the method to mark as undone the Duke.Task.Event task
     * @return Duke.Task.Event object
     */
    @Override
    public Event markUndone() {
        super.markUndone();
        return this;
    }

    /**
     * The method to change the tasklist format to write in tasks.txt
     * @return String
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "E | " + mark + " | " + this.description + " | " +
                this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Overridden toString method for Duke.Task.Event task details
     * @return String
     */
    @Override
    public String toString() {
        return "[E]"  + super.toString() +
                " (at: " + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")) + ")";
    }

    /**
     * The execute version to process given Duke.Task.Event task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui, Storage storage) {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
        storage.write(task.getTasks());
    }
}