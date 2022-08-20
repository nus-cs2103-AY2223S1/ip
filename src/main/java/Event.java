import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    protected String at = description.substring(description.lastIndexOf("/") + 4);

    private DateTimeFormatter formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date = LocalDate.parse(at, formatted);


    @Override
    public String toString() {
            return "[E]" + super.toString().substring(0, 4) + getSubstring()
                    +  "(at: " +  date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    private String getSubstring() {
        int index = description.indexOf("/");
        if (index != - 1) {
            return description.substring(6, index);
        }
        return null;
    }

    /*
    @Override
    public String saveString() {
        return "D " + "| " + getStringStatusIcon() + " | " + getSubstring() + "|" + at + "\n";
    }
    */
}
