package sally.task;

import java.time.LocalDate;

/**
 * ToDo class to represent new Todo task
 *
 * @author liviamil
 */

public class ToDo extends Task {
    protected LocalDate TODAY = LocalDate.now();
    protected LocalDate createdBy;

    /**
     * Constructor for todo tasks
     *
     * @param description description for the todo task
     */
    public ToDo(String description) {
        super(description);
        this.createdBy = LocalDate.now();
    }

    /**
     * Gets the output string for save to file
     *
     * @return output string for save to file
     */
    public String getOutput() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    public LocalDate getCreatedBy() {
        return createdBy;
    }

    public boolean isWithin(int days) {
        return getCreatedBy().compareTo(TODAY) <= days;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
