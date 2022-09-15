package Duke;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate timeobject;


    public Deadline(String description, String time) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDate date = LocalDate.parse(time,formatter);
        timeobject =date;
        System.out.println("printing time");
        System.out.println(timeobject.toString());

    }
    @Override
    public String storeToString() {
        return "D|" + this.binarytoString() + "|" + this.description.substring(0,description.length()-1) + "|" + this.timeobject.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by: " + this.timeobject.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }

}