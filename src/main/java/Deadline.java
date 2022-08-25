import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateTimeConverter.formatDate(by);
    }

    @Override
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "D | " + done + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DateTimeConverter.formatString(by) + ")";
    }
}
