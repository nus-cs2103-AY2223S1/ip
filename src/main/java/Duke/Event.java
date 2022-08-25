package Duke;

import java.time.LocalDate;

public class Event extends Task{
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = DateTimeConverter.formatDate(at);
    }

    @Override
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "E | " + done + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + DateTimeConverter.formatString(at) + ")";
    }
}
