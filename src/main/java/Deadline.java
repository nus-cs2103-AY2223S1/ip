import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateTimeConverter.formatDate(by.substring(1));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + DateTimeConverter.formatString(by) + ")";
    }
}
