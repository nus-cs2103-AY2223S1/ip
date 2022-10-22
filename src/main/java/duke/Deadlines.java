package duke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Deadlines extends Task {

    protected String deadlineby;

    /**
     * Returns you description
     * @param description
     */
    public Deadlines(String description) {
        super(description);
    }

    /**
     * Constructor
     * @param description
     * @param deadlineby
     */
    public Deadlines(String description,String deadlineby) {
        super(description);
        this.deadlineby = deadlineby;
    }

    public LocalDate getDateOnlyDeadLine() {
        String str = this.deadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        return dateTime;
    }

    /**
     * Converts string to date(Not implemented in GUI due to time constrain)
     * @return string converted to date
     */
    public LocalDateTime getDateTimeDeadLine() {
        String str = this.deadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    /**
     * Not implemented into the GUI, but it can understand the date and change it to the correct format
     * @return Date of correct format
     * @throws ParseException
     */
    public String getDateOnlyDeadLineNewFormat() throws ParseException {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";

        String oldDateString = this.deadlineby;
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        return newDateString = sdf.format(d);
    }

    public String getDeadLine() {
        return this.deadlineby;
    }

    /**
     *This method is used to get deadline description
     * @return String description
     */
    public String getDeadLineTask() {
        return description;
    }

    /**
     * Gets you the symbol for deadline
     * @return a string which is [D]
     */
    public String getItem() {
        return "[D]";
    }


}

