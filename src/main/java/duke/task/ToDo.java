package duke.task;

import java.time.LocalDate;

/**
 * The duke.task.ToDo class represents a task
 * the user specified that needs to be done.
 */
public class ToDo extends Task {
    /**
     * Constructs a duke.task.ToDo object.
     *
     * @param description description for the duke.task.ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }


    public ToDo(int i, String description) {
        super(description);
        if (i == 1) {
            this.markDone();
        }
    }

    @Override
    public String toStore() {
        return "T" + " | " + super.toStore();
    }

    @Override
    public boolean compareDate(LocalDate date) {
        return false;
    }

    /**
     * Overriding method of toString() for duke.task.ToDo.
     * @return the string representing duke.task.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
