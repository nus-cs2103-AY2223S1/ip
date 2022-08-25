package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String details, String deadline) {
        super(details);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String getDetails() {
        return details + " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String getEncodedDetails() {
        return details + "|" + deadline;
    }
}
