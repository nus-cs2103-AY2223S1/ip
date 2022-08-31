package tasks;

import utils.DateParser;

import java.time.LocalDate;

public class Deadline extends Task {

    private final LocalDate localDate;

    public Deadline(String name, String dateStr) {
        super(name);
        localDate = DateParser.stringToDate(dateStr);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + localDate.toString() + ")";
    }

}
