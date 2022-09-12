import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Deadlines extends Task {

    protected String deadlineby;

    public Deadlines(String description) {
        super(description);
    }

    public Deadlines(String description,String deadlineby) {
        super(description);
        this.deadlineby = deadlineby;
    }

    public LocalDate getDateonlyd() {
        String str = this.deadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        return dateTime;
    }

    public LocalDateTime getDateTimeD() {
        String str = this.deadlineby;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public String getDateonlyDnewformat() throws ParseException {
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

    public String getDeadLineTask() {
        return description;
    }

    public String getItem() {
        return "[D]";
    }


}

