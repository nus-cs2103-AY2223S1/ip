package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an Events task. A Event object contains the description and when event is happening
 */
public class Events extends TaskWithDate {
    /**
     * Constructor for Event
     * @param description String that describes the task
     * @param by LocalDateTime that denotes when event is happening
     */
    public Events(String description, LocalDateTime by) {
        super(description, by);
    }

    /**
     * @return String to save onto text document
     */
    @Override
    public String textFormat() {
        return "E|" + (isDone ? 1 : 0) + "|" + description + "|" + getTiming();
    }

    /**
     * @return String to be displayed to users
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + dateToString() + ")";
    }

}
