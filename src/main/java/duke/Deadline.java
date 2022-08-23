package duke;

import java.time.LocalDate;

public class Deadline extends Task {
    private static final String TYPE_SYMBOL = "[D]";
    private LocalDate deadline;

    public Deadline(String task, String deadline) throws DukeException {
        super(task);
        this.deadline = Parser.convertToDateObj(deadline);
    }

    public Deadline(String task, String deadline, boolean isDone) throws DukeException {
        super(task, isDone);
        this.deadline = Parser.convertToDateObj(deadline);
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString()
                + " (by: " + Parser.printDate(deadline) + ")";
    }

    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getTask()
                + " @ " + Parser.printSaveFileDate(deadline);
    }
}
