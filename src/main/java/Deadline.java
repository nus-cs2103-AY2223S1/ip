import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    protected String description;
    protected boolean isDone;
    protected String date;
    LocalDate localDate;

    /**
     * Constructor for a Deadline object
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        try {
            //reverts the format for file reading
            //not good to use exceptions as conditions though...
            this.localDate = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("MMM d yyyy"));
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String newFormat = dtFormatter.format(localDate);
            this.date = newFormat;
            this.localDate = LocalDate.parse(this.date);
        } catch (DateTimeParseException e) {
            try {
                this.localDate = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e1) {
                System.out.println("Please provide the date in the correct format, which is yyyy-MM-dd");
                return;
            }
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String newFormat = dtFormatter.format(localDate);
        return "[D]" + super.toString() + " (by: " + newFormat + ")";
    }
}
