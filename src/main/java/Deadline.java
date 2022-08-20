import java.time.LocalDate;

public class Deadline extends Task {
    /**
     * Child class of Task with a deadline
     */
    private static final String SYMBOL = "[D]";
    protected String dateStr;
    protected LocalDate dateTime;
    public Deadline(String description, String dateStr, LocalDate dateTime) {
        super(description);
        this.dateStr = dateStr;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(by: " + dateStr + ")";
    }
}
