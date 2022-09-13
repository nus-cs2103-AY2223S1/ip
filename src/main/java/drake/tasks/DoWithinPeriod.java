package drake.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents tasks that need to be done with a certain period.
 */
public class DoWithinPeriod extends Task {

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor.
     * @param description The task description.
     * @param from The starting date of the period.
     * @param to The ending date of the period.
     */
    public DoWithinPeriod(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public List<String> toList() {
        List<String> result = new ArrayList<>();
        result.add("W");
        result.addAll(super.toList());
        result.add(from.toString());
        result.add(to.toString());
        return result;
    }

    @Override
    public String toString() {
        return "[W]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ", "
                + "to: " + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
