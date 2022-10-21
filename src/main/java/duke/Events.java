package duke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Events extends Task {

    protected String Eventsdeadlineby;

    /**
     *Constructor for events
     * @param description
     */
    public Events(String description) {
        super(description);
    }

    public Events(String description,String Eventsdeadlineby) {
        super(description);
        this.Eventsdeadlineby = Eventsdeadlineby;
    }

    /**
     *Helps in the creation of date (Not implemented due to time constrain)
     * @return converts date from string to date
     */
    public LocalDate getDateOnlyEvents() {
        String str = this.Eventsdeadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        return dateTime;
    }

    /**
     *Give the date in a different format
     * @return the date in a different format
     * @throws ParseException
     */
    public String getDateOnlyEventsNewFormat() throws ParseException {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";

        String oldDateString = this.Eventsdeadlineby;
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        return newDateString = sdf.format(d);
    }

    /**
     *Date time for event
     * @return local date time for event
     */

    public LocalDateTime getDateTimeEvents() {
        String str = this.Eventsdeadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    /**
     *Gives the deadline as a string
     * @return event dateline as a string
     */
    public String getEventDeadlineString() {
        return this.Eventsdeadlineby;
    }

    /**
     *The name of the event
     * @return name of event
     */
    public String getEventsDescription() {
        return description;
    }

    /**
     * Same as getEventDeadlineString
     * @return event dateline as a string
     */
    public String getEvent() {
        return this.Eventsdeadlineby;
    }

    /**
     * Gives u the symbol to store in text file
     * @return String which is [E]
     */
    public String getItem() {
        return "[E]";
    }

}
