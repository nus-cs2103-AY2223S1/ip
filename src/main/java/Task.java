import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    private final String description;
    private boolean isComplete;

    Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    Task(String description) {
        this(description, false);
    }

    void mark() {
        this.isComplete = true;
    }

    void unmark() {
        this.isComplete = false;
    }

    String getDescription() {
        return this.description;
    }

    String getStatusIcon() {
        return isComplete ? "[X]" : "[ ]";
    }

    int getStatusNumber() {
        return isComplete ? 1 : 0;
    }

    String toStorageFormat() {
        return this.getStatusNumber() + " | " + this.description;
    }

    static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date format is invalid!", e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), description);
    }
}
