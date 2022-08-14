/**
 * This class encapsulates a deadline set by the user.
 */
public class Deadline extends Task {
    private String date;

    Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    /**
     * Returns the String representation of this deadline.
     *
     * @return A String representing this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
