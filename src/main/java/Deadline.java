import javax.swing.text.DateFormatter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Formatter;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }



    @Override
    public String toString() throws DateTimeException {


        LocalDate d1 = LocalDate.parse(by.substring(1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String deadline = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }



}