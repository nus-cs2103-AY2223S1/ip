import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String dateTime = "";
    private LocalDateTime dateTimeProper;
    public Event(String taskDescription, String dateTime) {
        super(taskDescription.replace("event ", ""));
        this.dateTime = dateTime;
        this.dateTimeProper = LocalDateTime.parse(dateTime);
    }
    @Override
    protected String returnDescription() {
        String formattedDate = this.dateTimeProper.format(DateTimeFormatter.ofPattern("MMM dd yyyy @ HH:mm"));
        return "[E]" + super.returnDescription() + "(at: " + formattedDate + ")";
    }
}
