import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws InvalidDeadlineException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    public Deadline(String description, String by, boolean completed) throws InvalidDeadlineException {
        super(description, completed);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }
    public String getFormattedDate() {
        return this.by.format(DateTimeFormatter.ofLocalizedDate(Task.DATE_FORMAT));

    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " +  this.getFormattedDate()
                + ")";
    }

    @Override
    public String toSaveString() {
        return "D," + super.toSaveString() + String.format(",%s", this.by);
    }

    public static Deadline parse(String data) throws ParsingTaskException {
        String[] components = data.split(",");
        if (components.length != 4) {
            throw new ParsingTaskException(String.format("Todos require 4 components, but found %d.", components.length));
        }
        try {
            boolean completed = Integer.parseInt(components[1]) == 1;

            String description = components[2];
            String by = components[3];

            return new Deadline(description, by, completed);
        } catch (NumberFormatException e) {
            throw new ParsingTaskException(String.format("Expected a number at component 1, but found %s", components[1]));
        } catch (InvalidDeadlineException e) {
            throw new ParsingTaskException(e.getMessage());
        }
    }
}