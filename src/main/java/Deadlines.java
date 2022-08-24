import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Date;

public class Deadlines extends Task {

    private LocalDate localDate;

    public Deadlines(String deadline, String dateStr) throws ParseException {
        super(deadline);
        try {
            this.localDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e){
            System.out.println("Please input dates in the following format: yyyy-mm-dd (eg. 2019-10-15)");

        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

