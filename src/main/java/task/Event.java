package task;

import duke.DukeException;
import ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected UI ui = new UI();
    protected String at;
    protected String string_Date;
    protected String string_Time;

    protected LocalDate date;

    /**
     * Creates a new Event
     * @param description The activity of the event.
     * @param at The date of the event.
     */

    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] splitAt = at.split(" ");
        DateTimeFormatter formatter;
        try {
            if (splitAt.length == 4) {
                this.string_Date = splitAt[1] + "/" + splitAt[0] + "/" + splitAt[2];
                this.string_Time = splitAt[3];
                formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
            } else {
                this.string_Date = splitAt[0];
                this.string_Time = splitAt[1];
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.noDateOrTime());
        }
        try {
            this.date = LocalDate.parse(string_Date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.wrongDateTimeFormat());
        }
    }

    public boolean isDueSoon() {
        LocalDate currentDate = LocalDate.now();
        LocalDate deadLine = currentDate.plusDays(6);
        return (!date.isAfter(deadLine) && !date.isBefore(currentDate));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String output = date.format(formatter1);
        return "[E]" + super.toString() + " (at: " + output + " " + string_Time + ")";
    }
}
