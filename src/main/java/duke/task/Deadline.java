package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }


    @Override
    public String formatTask() {
        return "[D] [" + super.getStatusIcon() + "] " + super.description + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String toString() {
        return "D/" + super.getStatusIcon() + "/" + super.description + "/" + by;
    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }

        public ToDo(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String formatTask() {
            return "[T] [" + super.getStatusIcon() + "] " + super.description;
        }

        @Override
        public String toString() {
            return "T/" + super.getStatusIcon() + "/" + super.description;
        }
    }
}
