package duke.events;

import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final String TYPE = "[E]";
    protected LocalDate at;
    private String unformattedDate;

    public Event(String description, String unformattedDate) {
        super(description);
        this.unformattedDate = unformattedDate;
        this.at = LocalDate.parse(unformattedDate); //TODO: figure out how to parse multiple types
    }

    public Event(boolean isDone, String description, String unformattedDate) {
        super(isDone, description);
        this.unformattedDate = unformattedDate;
        this.at = LocalDate.parse(unformattedDate);
    }


    public static Event readTask(String[] values) {
        boolean isDone = values[1].equals("0");
        String description = values[2];
        String unformattedDate = values[3];
        return new Event(isDone, description, unformattedDate);

    }

    @Override
    public String savableString() {
        return "E" + super.savableString() + "//" + unformattedDate;
    }

    /**
     * Formats the data nicely using the LocalData library
     * And an appropriately formatted string
     * @param ld
     * @return Event
     */


    public String customFormatter(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {

        return TYPE + super.toString() + "(at: " + customFormatter(at) + ")";
    }
}