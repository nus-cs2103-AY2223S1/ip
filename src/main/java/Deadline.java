import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")";
    }

}
