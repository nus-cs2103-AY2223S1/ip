package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be completed by a deadline.
 */
public class Deadline extends Task {

    private String name;
    private String type;
    private String status;
    private LocalDateTime time;
    private char priority;
    private Duke duke = new Duke();

    /**
     * Constructs a task with deadline.
     *
     * @param name name of the task
     * @param deadline deadline of the task
     */
    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[D]";
        this.time = deadline;
        this.priority = 'L';
    }

    /**
     * Sets the status of the task as specified
     *
     * @param status the status of the task. [X] for done, [ ] for undone.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the priority of the task.
     * @param priority the priority of the task.
     */
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority of the task.
     * @return the priority of the task.
     */
    public char getPriority() {
        return this.priority;
    }

    /**
     * Prints the description of the deadline task that is being added to the task list.
     */
    public void print() {
        System.out.println(Ui.ADD_TASK_HEADER + this.toString()
                + " Now you have " + duke.getCount() + " tasks in the list." + "\n" + Duke.LINE + "\n");
    }

    /**
     * Returns the description of the deadline task that is being added to the task list.
     * @return the description of the deadline task that is being added to the task list.
     */
    @Override
    public String printGui() {
        return (Ui.ADD_TASK_HEADER_GUI + this.toString() + " Now you have "
                + duke.getCount() + " tasks in the list." + "\n");
    }

    /**
     * Prints the description of the deadline task.
     */
    public void list() {
        System.out.println(this.type + this.status + " " + this.priority
                + this.name + "(by: " + formatDateString(this.time) + ")");
    }

    /**
     * Returns the description of the deadline task.
     * @return the description of the deadline task.
     */
    public String listGui() {
        return this.type + this.status + " " + this.priority + " "
                + this.name + "(by: " + formatDateString(this.time) + ")";
    }

    /**
     * Gets the deadline name.
     *
     * @return the string representation of the deadline name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the deadline status.
     *
     * @return the string representation of the deadline status.
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets the deadline type.
     *
     * @return the string representation of the deadline type.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Returns the full string representation of the deadline task.
     *
     * @return the complete string representation of the deadline task.
     */
    public String toString() {
        return this.getType() + this.getStatus() + " " + this.getPriority() + " " + this.getName()
                + " (by: " + formatDateString(this.time) + ")" + "\n";
    }

    private String formatDateString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of the deadline task.
     */
    public String printDescription() {
        return this.getName() + " (by: " + formatDateString(this.time) + ")";
    }
}
