import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String date;

    public Deadline(String description, String by, boolean dateIsFormatted) throws DukeException {
        super(description);
        if (dateIsFormatted == true) {
            this.date = by;
        } else {
            this.date = getLocalDateTime(by, "deadline", "/by").format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a"));
        }
    }

    @Override
    public String getDate() {
        return this.date;
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
