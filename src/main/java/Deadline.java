import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String name, String deadline) throws DukeException {
        super(name);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use this format for the deadline: YYYY-MM-DD");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
