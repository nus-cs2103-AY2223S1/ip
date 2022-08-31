package ip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    protected LocalDateTime by;

    Deadline(String description, String by) {
        this(description, by, false);
    }

    protected Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("EE, dd MMM yyyy, HH:mm"));
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[D]%s (by: %s)", parStr, getBy());
    }
}
