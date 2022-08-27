import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, String at) throws UwuException {
        super(description);
        this.at = new UwuDateTime(at).getDateTime();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " +
                at.format(formatter) + ")";
    }

    @Override
    public String toStorageString() {
        String isDoneIndicator = super.isDone ? "1" : "0";
        String atString = at.toString().replaceAll("T", " ");

        return "E," + isDoneIndicator + "," + description.trim() + "," + atString;
    }
}
