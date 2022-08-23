package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String isDone, String description, String dateStr) {
        super(description, isDone.equals("1"));
        this.date = LocalDate.parse(dateStr);
    }

    @Override
    public String toStringSaveFormat() {
        return String.format("D,%s,%s,%s\n", this.isDone ? "1" : "0", this.description, this.date);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.description, this.date);
    }
}
