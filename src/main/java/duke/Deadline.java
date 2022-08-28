package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public String dl;
    public LocalDate exactTime;

    public Deadline(String name, String dl) {
        super(name);
        this.dl = dl;
        try {
            this.exactTime = LocalDate.parse(dl);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong input format!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + exactTime.getMonth() + " "
                + exactTime.getDayOfMonth() + " " + exactTime.getYear() + ")";
    }
}