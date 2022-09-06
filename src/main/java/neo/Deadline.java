package neo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline task.
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for deadline class.
     *
     * @param description stores user input
     * @param by stores date string
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns deadlines task string in specific format.
     *
     * @return String
     */
    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(by);
        return "[D]" + super.toString() + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
