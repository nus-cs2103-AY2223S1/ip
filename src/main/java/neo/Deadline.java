package neo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline task.
 */
public class Deadline extends Task{
    protected String by = "";

    /**
     * Constructor for deadline class.
     *
     * @param description Stores user input.
     * @param by Stores date string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for deadline class.
     *
     * @param description Stores user input.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Returns deadlines task string in specific format.
     *
     * @return String
     */
    @Override
    public String toString() {
        if (by.equals("")){
            return "[D]" + super.toString();
        }
        else {
            LocalDate d1 = LocalDate.parse(by);
            return "[D]" + super.toString() + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
