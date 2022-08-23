package duke;

import java.time.LocalDate;

public class Event extends Task {
    private static final String TYPE_SYMBOL = "[E]";
    private LocalDate date;

    public Event(String task, String date) throws DukeException {
        super(task);
        this.date = Parser.convertToDateObj(date);
    }

    public Event(String task, String date, boolean isDone) throws DukeException {
        super(task, isDone);
        this.date = Parser.convertToDateObj(date);
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString()
                + " (at: " + Parser.printDate(date) + ")";
    }

    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getTask()
                + " @ " + Parser.printSaveFileDate(date);
    }
}
