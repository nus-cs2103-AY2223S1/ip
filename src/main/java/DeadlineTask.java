import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeadlineTask extends Task {
    protected LocalDate by;

    DeadlineTask(String action, boolean isDone, String by) throws DukeException {
        super(action, isDone, by);
        if (Objects.equals(by, "")) {
            throw new DukeException();
        }
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    DeadlineTask(String action, String by) throws DukeException {
        this(action, false, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }
}
