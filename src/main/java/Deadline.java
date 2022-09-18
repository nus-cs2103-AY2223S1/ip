/**
 * Deadlines are tasks that have a description and must be done by a certain time.
 *
 * @author AkkFiros
 */
public class Deadline extends Task {
    private static final String DEADLINE_SYMBOL = "D";
    protected String date;

    /**
     * Constructor for Deadline task
     * @param description description of a task that the user inputs
     * @param by the cut-off time for user to complete the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = date;
    }

    /**
     * toString method for a Deadline task
     * @return string representation of a Deadline task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", Deadline.DEADLINE_SYMBOL,
                super.toString(), Parser.parseDate(date));
    }

    /**
     * Method to retrieve a Deadline task's type
     * @return Symbol representation of a Deadline task
     */
    @Override
    public String getType() {
        return Deadline.DEADLINE_SYMBOL;
    }

    /**
     * Retrieves the cut off time of a deadline task
     * @return the cut off time of a task
     */
    @Override
    public String getDate() {
        return date;
    }

}
