package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final char tag = 'E';
    public static final String DELIMITER = " /at ";
    private String time;
    private LocalDate date;

    public Event(String description) {
        super(description.split(Event.DELIMITER)[0].substring(6));
        this.time = description.split(Event.DELIMITER)[1];
        this.date = LocalDate.parse(this.time);
    }

    @Override
    public String printTask() {
        return String.format("[%s]%s (at: %s)",
                this.tag,
                super.printTask(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
