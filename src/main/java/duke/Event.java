package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.ImproperEventFormatException;

public class Event extends Task {

    private LocalDate date;
    private LocalTime time;

    private String at;

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("h:mm a");

    /*
     * Create Event with description, date in MMM DD YYYY, time in hh:mm aa
     * @throws ImproperFormatException when by does not follow specified format
     */
     
    public Event(String description, String at) throws ImproperEventFormatException {
        super(description);
        this.at = at;
        try {
            String[] arr = at.split(" ");
            this.date = LocalDate.parse(arr[1]);
            this.time = LocalTime.parse(arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperEventFormatException();
        } catch (DateTimeParseException e) {
            throw new ImproperEventFormatException();
        }
    }

    /*
     * @return String representation in
     *         "[T] [status] task (at: MMM DD YYYY h:mm a)"
     */
     
    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " "
                + super.description
                + " (at: "
                + date.format(DATE_FORMAT)
                + ", "
                + time.format(TIME_FORMAT)
                + ")";
    }

    /*
     * return String representation of event to be stored in Storage
     * @return String representation in
     *         "T|0 or 1|task|at|"
     *         where 1 represents the task is marked and 0 otherwise
     */
     
    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "E|1|"
                    + super.description
                    + "|"
                    + this.at
                    + "\n";
        } else {
            return "E|0|"
                    + super.description
                    + "|"
                    + this.at
                    + "\n";
        }
    }
    
    private LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date, this.time);
    }

    public int compareChronologically(Event event) {
        return this.getDateTime().compareTo(event.getDateTime());
    }

    public int compareLexicographically(Event event) {
        return this.description.compareTo(event.description);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event x = (Event) obj;
            if (this.description == null
                    || this.at == null
                    || x.description == null
                    || x.at == null) {
                return false;
            }
            return this.description.equals(x.description)
                    && this.at.equals(x.at);
        }

        return false;
    }
}
