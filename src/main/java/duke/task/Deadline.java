package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String name, String date) throws DukeException {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use this format for the deadline: YYYY-MM-DD");
        }
    }

    public Deadline(String name, String date, boolean isDone) {
        super(name, isDone);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static Deadline fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7, inputString.indexOf("(by"));
        String dateString = inputString.substring(inputString.indexOf("(by: ") + 5, inputString.length() - 1);
        return new Deadline(name, dateString, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
