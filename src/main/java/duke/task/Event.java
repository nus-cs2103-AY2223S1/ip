package duke.task;

import java.time.LocalDate;

import duke.common.Utils;

public class Event extends Task {

    private final LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = Utils.parseDate(at);
    }

    @Override
    public String encodeToString() {
        String taskStatus = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s", taskStatus, description, at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", super.getStatusIcon(), description, Utils.convertLocalDate(at));
    }
}