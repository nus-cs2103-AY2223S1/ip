import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    private String by;
    private LocalDate dateBy;

    public Deadline(String name, boolean isDone, String by) throws DukeException {
        super(name, isDone);
        if (by.equals("") || by.equals(" ")) {
            throw new DukeException("time can't be empty");
        }
        this.by = by;
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dateBy = LocalDate.parse(by);
        } else {
            throw new DukeException("invalid date format");
        }
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[D]" + temp + " (by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
