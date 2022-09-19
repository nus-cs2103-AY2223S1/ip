package pony.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
public class Event extends Task {

    private String timeInfo;
    private LocalDateTime time;

    /**
     * Constructor for Event.
     *
     * @param description Task description.
     * @param timeInfo Time the task is happening.
     * @throws DateTimeParseException timeInfo input not following the correct format.
     */
    public Event(String description, String timeInfo) throws DateTimeParseException {
        super(description);
        this.timeInfo = timeInfo;
        this.time = LocalDateTime.parse(timeInfo, Task.FORMAT_INPUT);
    }
    private String formatTime() {
        return this.time.format((FORMAT_OUTPUT));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatTime() + ")";
    }

    @Override
    public String saveToDisk() {
        String output = "";
        String taskStatus = (this.isDone) ? "1" : "0";
        output += "D" + SEPARATOR + taskStatus + SEPARATOR + this.description + SEPARATOR + this.timeInfo + "\n";
        return output;
    }
}