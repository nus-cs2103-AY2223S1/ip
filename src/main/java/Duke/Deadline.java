package Duke;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate timeobject;


    public Deadline(String description, String time) {
        super(description);
        this.timeobject = Parser.stringToDate(time);

    }
    @Override
    public String storeToString() {
        return "D|" + this.binarytoString() + "|" + this.description.substring(0,description.length()-1) + "|"
                + Parser.dateToString(this.timeobject);
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by: " + Parser.displayDate(timeobject) + ")";
    }

}