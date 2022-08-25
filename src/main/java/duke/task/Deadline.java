package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;


public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static Deadline parseFile(String data) throws DukeException {
        try {
            String[] details = data.split(" \\| ");
            Deadline deadline = new Deadline(details[2], LocalDate.parse(details[3]));
            if (details[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format (yyyy-mm-dd)");
        }
    }

    @Override
    public String toDataFormat() {
        String completed = "";
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        return "D | " +  completed + " | " + this.getDescription() + " | " + this.by;
    }


    @Override
    public boolean isOn(LocalDate date) {
        return this.by.equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")) + ")";
    }
}