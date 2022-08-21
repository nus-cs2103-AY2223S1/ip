import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Deadline date of the task */
    private String date;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.date = getDate(by).format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm a"));
    }

    /**
     * Returns String representation of the task with deadline.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}