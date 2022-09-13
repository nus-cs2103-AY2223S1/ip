package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final String SHORTHAND = "D";

    private LocalDateTime by;

    Deadline(String description, String by) {
        super(description, SHORTHAND);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }

    public Deadline(String description, LocalDateTime by) {
        super(description, SHORTHAND);
        this.by = by;
    }

    @Override
    public String getStorageString() {
        String parentStorageString = super.getStorageString();
        return String.format("%s|%s", parentStorageString, getByStorage());
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("%s (by: %s)", parStr, getByStr());
    }

    private String getByStr() {
        return this.by.format(DateTimeFormatter.ofPattern(PRINT_TIME_FORMAT));
    }

    private String getByStorage() {
        return this.by.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }
}
