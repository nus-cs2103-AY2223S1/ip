package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalTime start_time;
    private LocalTime end_time;


    public Event(String description, String at) throws DateTimeException {
        super(description);
        this.at = at;
        String[] split = at.split(" ");
        this.start_date = LocalDate.parse(split[0]);
        this.start_time = LocalTime.parse(split[1]);
        this.end_date = LocalDate.parse(split[2]);
        this.end_time = LocalTime.parse(split[3]);
    }

    @Override
    public String toString() {
        String start_d = start_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String end_d = end_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[E]" + super.toString() + " (at: " + start_d + " " + start_time + " - "
                + end_d + " " + end_time + ")";
    }

    @Override
    public String toSaveData() {
        return "E" + " | " + super.toSaveData() + " | " + at;
    }
}
