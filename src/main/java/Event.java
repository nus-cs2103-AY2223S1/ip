import java.time.LocalDate;


public class Event extends Task {
    private LocalDate date;

    public Event(String name, LocalDate date) {
        /**
         * Constructor for Event class. Sets the event name and time period.
         *
         * @param name The task name.
         * @param period The time period where the event will take place.
         */
        super(name);
        this.date = date;
    }

    public boolean isOnDate(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String toString() {
        /**
         * String representation of an event. Also indicates if the event is done.
         */
        return "[E]" + super.toString() + " (at: " + this.date.toString() + ")";
    }

    public LocalDate getDate() {
        return this.date;
    }
}
