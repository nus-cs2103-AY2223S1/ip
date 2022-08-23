package duke.task;

import java.time.LocalDate;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageRepresentation() {
        return "T|" + super.toStorageRepresentation();
    }

    /**
     * Returns false as ToDo task doesn't have any date associated with it.
     *
     * @param selectedDate the date t
     * @return false.
     */
    @Override
    public boolean isOnGivenDate(LocalDate selectedDate) {
        return false;
    }
}
