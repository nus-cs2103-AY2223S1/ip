package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    LocalDate timeobject;

    public Event(String description, String time) {
        super(description);
        this.timeobject = Parser.stringToDate(time);
    }

    public String storeToString() {
        return "E|" + this.binarytoString() + "|" + this.description.substring(0,description.length()-1) + "|" + Parser.dateToString(this.timeobject);
    }

    @Override
    public String toString() {
        return "[E]"  + super.toString() + "(at: " + Parser.displayDate(timeobject) + ")";
    }
}
