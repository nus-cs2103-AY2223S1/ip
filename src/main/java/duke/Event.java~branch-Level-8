package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    LocalDate timeobject;

    public Event(String description, String time) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDate date = LocalDate.parse(time,formatter);
        timeobject =date;
    }

    public String storeToString() {
        return "E|" + this.binarytoString() + "|" + this.description.substring(0,description.length()-1) + "|" + this.timeobject;
    }

    @Override
    public String toString() {
        return "[E]"  + super.toString() + "(at: " + this.timeobject.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}
