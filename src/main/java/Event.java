import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public static Event parseFile(String data) throws DukeException{
        String[] details = data.split(" \\| ");
        try {
            Event event = new Event(details[2], LocalDate.parse(details[3]));
            if (details[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format (yyyy-mm-dd)");
        }
    }

    @Override
    public String toDataFormat() {
        String completed = "";
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        return "E | " +  completed + " | " + this.getDescription() + " | " + this.at;
    }

    @Override
    public boolean isOn(LocalDate date) {
        return this.at.equals(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")) + ")";
    }
}