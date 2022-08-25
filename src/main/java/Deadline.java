import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor for the deadline type of task.
     *
     * @param description description of the deadline task
     * @param date the deadline timing of the deadline task
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * toString method of a deadline task.
     *
     * @return the string representation of a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.getMonth() + " "
                + date.getDayOfMonth() + " " + date.getYear() + ")";
    }
}
