import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws InvalidCommandException{
        super(description);
        String[] formatPatterns = {"yyyy-MM-dd", "dd-MMM-yyyy", "dd/MM/yyyy", "E, MMM dd yyyy"};
        for (int i = 0; i < formatPatterns.length; i++) {
            try {
                LocalDate d = LocalDate.parse(by, DateTimeFormatter.ofPattern(formatPatterns[i]));
                this.by = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                break;
            } catch (DateTimeParseException e) {
                if(i == formatPatterns.length - 1) {
                    throw new InvalidCommandException("Invalid Date Time input into Deadline Task");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getTaskDescription() + " (by: " + by + ")";
    }
}
