package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public String eventTime;
    public LocalDate exactTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
        try {
            this.exactTime = LocalDate.parse(eventTime);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong input format!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + exactTime.getMonth() + " "
                + exactTime.getDayOfMonth() + " " + exactTime.getYear() + ")";
    }
}
