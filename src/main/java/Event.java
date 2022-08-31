import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");
    protected static DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(at, dateTimeInputFormatter);
        } catch (Exception e) {
            throw new DukeException("Input your date and time in the format yyyy-MM-dd HHmm!");
        }
    }

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public static Event createEventFromString(String line) {
        return new Event(line.substring(10, line.indexOf("(at:") - 1),
                LocalDateTime.parse(line.substring(line.indexOf("(at:") + 5, line.lastIndexOf(")")),
                        dateTimeOutputFormatter));
    }

    public boolean isOnThisDate(String dateStr) throws DukeException {
        if (dateStr.isBlank()) {
            throw new DukeException("Date cannot be blank!");
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, dateInputFormatter);
            return date.equals(this.dateTime.toLocalDate());
        } catch (Exception e){
            throw new DukeException("Please type in the date in the format yyyy-MM-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(dateTimeOutputFormatter) + ")";
    }
}