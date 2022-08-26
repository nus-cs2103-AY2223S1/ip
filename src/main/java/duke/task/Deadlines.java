package duke.task;

import java.time.LocalDate;
import java.time.Month;

public class Deadlines extends Task {
    private LocalDate byDate;
    public Deadlines(String name, LocalDate byDate) {
        super(name);
        this.byDate = byDate;
    }

    @Override
    public String writeData() {
        int mark = isDone ? 1 : 0;
        return "D#" + mark + "#" + this.name + "#" + this.byDate;
    }

    @Override
    public String toString() {
        int year = byDate.getYear();
        int day = byDate.getDayOfMonth();
        Month month = byDate.getMonth();
        return "[D]" + super.toString() + " (by: " + day + " " + month + " " + year + ")";
    }
}
