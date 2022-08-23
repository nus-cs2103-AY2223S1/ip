import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate d;
    private LocalTime t;
    private boolean hasTime;

    Deadline(String description, String by) throws DukeException {
        super(description);
        String[] dateAndTime = by.split(" ");
        try {
            this.d = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                this.t = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));
                this.hasTime = true;
            } else {
                this.hasTime = false;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time YYYY-MM-DD HHMM");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + this.d.format(DateTimeFormatter.ofPattern(("MMM d yyyy"))) 
                + (this.hasTime ? " " + this.t : "") 
                + ")";
    }
}
