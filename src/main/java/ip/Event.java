package ip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {

    protected LocalDateTime at;

    Event(String description, String at) {
        this(description, at, false);
    }

    protected Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }



    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("EE, dd MMM yyyy, HH:mm"));
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[E]%s (at: %s)", parStr, getAt());
    }
}
