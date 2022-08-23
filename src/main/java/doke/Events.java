package doke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    private LocalDate at;

    public Events(String description, String at) {
        super(description);
        LocalDate date = LocalDate.parse(at);
        this.at = date;
    }

    @Override
    public LocalDate getTime() {
        return at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }
}
