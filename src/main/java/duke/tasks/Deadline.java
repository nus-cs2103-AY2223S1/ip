package duke.tasks;

import utils.DukeUtils;

import java.time.LocalDate;

public class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String description) {
        super(description.split("/by")[0].trim());
        this.by = DukeUtils.parseDate(description.split("/by")[1].trim());
    }

    @Override
    public String savedString() {
        String status = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s", status, description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getStatusIcon(), description, DukeUtils.convertLocalDate(by));
    }
}