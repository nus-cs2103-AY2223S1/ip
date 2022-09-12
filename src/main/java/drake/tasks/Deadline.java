package drake.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected final LocalDate by;

    /**
     * Constructor.
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public List<String> toList() {
        List<String> result = new ArrayList<>();
        result.add("D");
        result.addAll(super.toList());
        result.add(by.toString());
        return result;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
