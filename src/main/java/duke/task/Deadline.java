package duke.task;

import duke.common.Utils;

import java.time.LocalDate;

public class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = Utils.parseDate(by);
    }

    @Override
    public String encodeToString() {
        String taskStatus = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", taskStatus, description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getStatusIcon(), description, Utils.convertLocalDate(by));
    }
}