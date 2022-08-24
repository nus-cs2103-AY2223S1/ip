import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task{
    protected LocalDate atDate;
    protected LocalTime atTime = null;

    public Event(String description, String at) {
        super(description);
        String[] arr = at.split(" ");
        this.atDate = LocalDate.parse(arr[0], Task.INPUT_DATE_FORMAT);
        if (arr.length != 1) {
            this.atTime = LocalTime.parse(arr[1], Task.INPUT_TIME_FORMAT);
        }
    }

    private String parseDate() {
        String output = this.atDate.format(Task.OUTPUT_DATE_FORMAT);
        if (this.atTime != null) {
            output += " " + this.atTime.format(Task.OUTPUT_TIME_FORMAT);
        }
        return output;
    }

    public String format() {
        return "event " + this.description + " /at " + this.at + "|" + this.getStatusIcon();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.parseDate() + ")";
    }
}
