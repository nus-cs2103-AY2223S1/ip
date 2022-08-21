import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Event date of the task */
    private String date;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.date = getDate(at).format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm a"));
    }

    /**
     * Returns String representation of the event details.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
