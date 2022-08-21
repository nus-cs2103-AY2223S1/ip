import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent "Deadline" tasks.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * The constructor for Deadline task
     * @param description
     * @param isDone
     */
    public Deadline(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * the method to mark as done the Deadline task
     * @return Deadline object
     */
    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    /**
     * the method to mark as undone the Deadline task
     * @return Deadline object
     */
    @Override
    public Deadline markUndone() {
        super.markUndone();
        return this;
    }

    /**
     * The method to change the tasklist format to write in tasks.txt
     * @return String
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "D | " + mark + " | " + this.description + " | " + this.time;
    }

    /**
     * Overridden toString method for Deadline task details
     * @return String
     */
    @Override
    public String toString() {
        return "[D]"  + super.toString() +
                " (by: " + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")) + ")";
    }

    /**
     * The execute version to process given deadline task
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