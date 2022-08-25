package duke.task;

import java.time.LocalDate;
import java.time.Month;

public class Events extends Task {
    private LocalDate atDate;
    Events(String name, LocalDate atDate) {
        super(name);
        this.atDate = atDate;
    }

    @Override
    public String writeData() {
        int mark = done ? 1 : 0;
        return "E#" + mark + "#" + this.name + "#" + this.atDate;
    }

    @Override
    public String toString() {
        int year = atDate.getYear();
        int day = atDate.getDayOfMonth();
        Month month = atDate.getMonth();
        return "[E]" + super.toString() + " (at: " + day + " " + month + " " + year + ")";
    }
}
