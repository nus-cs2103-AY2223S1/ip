import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, LocalDate date) {
        /**
         * Constructor for Deadline class. Sets the deadline name and date.
         *
         * @param name The task name.
         * @param date The date that the task is due.
         */
        super(name);
        this.date = date;
    }

    public boolean isOnDate(LocalDate date) {
        return this.date.equals(date);
    }

    public String toString() {
        /**
         * String representation of a deadline. Also indicates if the deadline is done.
         */
        return "[D]" + super.toString() + " (by: " + this.date.toString() + ")";
    }
}
