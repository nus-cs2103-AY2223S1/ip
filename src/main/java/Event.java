import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{

    protected String at;

    public Event(String description) {
        super(description.substring(6, description.indexOf('/') - 1));
        this.at = description.substring(description.indexOf('/') + 3);
    }

    @Override
    public String fileFormat() {
        return String.format("event | %s | %s | %b", super.description, at, super.isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
