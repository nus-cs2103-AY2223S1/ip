package tasks;

import utils.DateParser;

import java.time.LocalDate;

public class Event extends Task {

    private final LocalDate localDate;

    public Event(String name, String dateStr) {
        super(name);
        localDate = DateParser.stringToDate(dateStr);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + localDate.toString() + ")";
    }

}
