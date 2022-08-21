package dobby.tasks;

import dobby.commands.*;
import dobby.*;

public class Deadline extends Task {
    private String date;

    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }
    public Deadline(String task, String date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }
    @Override
    public String toPrint() {
        return "D" + super.toPrint() + " | " + date;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
