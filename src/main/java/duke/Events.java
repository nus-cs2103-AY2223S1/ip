package duke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Events extends Task {

    protected String Eventsdeadlineby;

    public Events(String description) {
        super(description);
    }

    public Events(String description,String Eventsdeadlineby) {
        super(description);
        this.Eventsdeadlineby = Eventsdeadlineby;
    }
    public LocalDate getDateonlyE() {
        String str = this.Eventsdeadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        return dateTime;
    }

    public String getDateonlyEnewformat() throws ParseException {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";

        String oldDateString = this.Eventsdeadlineby;
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        return newDateString = sdf.format(d);
    }



    public LocalDateTime getDateTimeE() {
        String str = this.Eventsdeadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public String getEventDeadlineString() {
        return this.Eventsdeadlineby;
    }



    public String getEventsDescription() {
        return description;
    }

    public String getEvent() {
        return this.Eventsdeadlineby;
    }

    public String getItem() {
        return "[E]";
    }





}
