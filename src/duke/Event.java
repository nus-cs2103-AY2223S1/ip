package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.ImproperFormatException;

public class Event extends Task {

    private LocalDate date;
    private LocalTime time;

    public Event(String description, String at) throws ImproperFormatException {
        super(description);
        try {
            String[] arr = at.split(" ");
            this.date = LocalDate.parse(arr[1]);
            this.time = LocalTime.parse(arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperFormatException();
        } catch (DateTimeParseException e) {
            throw new ImproperFormatException();
        }
    }

    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " "
                + super.description
                + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ", "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"))
                + ")";
    }
}
