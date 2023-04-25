package mort.task;

import java.time.LocalDate;

/**
 * Class that represents a to-do.
 */
public class ToDo extends Task {
    /**
     * Constructor to initialize the description and completion status of the to-do.
     * Completion status is always false when to-do is first created.
     * @param desc The to-do description.
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Constructor to initialize the description and completion status of the to-do.
     * @param desc The to-do description.
     * @param isDone The to-do completion status.
     */
    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public boolean isDateMatch(LocalDate date) {
        return false;
    }

    /**
     * Converts the to-do to its saved format.
     * @return The string representation of the saved format of the to-do.
     */
    @Override
    public String getSaveFormat() {
        return "T " + super.getSaveFormat();
    }

    /**
     * Returns the string representation of the to-do.
     * @return The string representation of the to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
