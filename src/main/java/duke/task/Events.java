package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private LocalDateTime timing;
    private static DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor that creates Event object with specified description, timing, and isDone status.
     *
     * @param description Description of Event.
     * @param timing Timing of the Event.
     * @param isDone isDone status of the Event.
     * @throws DateTimeParseException Exception thrown when format of input date is invalid.
     */
    public Events(String description, LocalDateTime timing, boolean isDone) throws DateTimeParseException{
        super(isDone);
        this.description = description;
        this.timing = timing;
    }

    /**
     * Method converts Event into String representation that is stored in the storage.
     *
     * @return String representation of Event.
     */
    @Override
    public String processData() {
        String str;
        if (this.getIsDone()){
            str = String.format("E|true|%s|%s|", this.getDescription(), this.timing.format(DATE_TIME_INPUT_FORMAT));
        } else {
            str = String.format("E|false|%s|%s|", this.getDescription(), this.timing.format(DATE_TIME_INPUT_FORMAT));
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()){
            str = String.format("[E] %s [X] (at %s)", this.getDescription(),
                    this.timing.format(DATE_TIME_OUTPUT_FORMAT));
        } else {
            str = String.format("[E] %s [ ] (at %s)", this.getDescription(),
                    this.timing.format(DATE_TIME_OUTPUT_FORMAT));
        }
        return str;
    }
}
