package duke.tasks;

import duke.services.Parser;

/**
 * Tasks that occur at a certain time
 */
public class Event extends Task {

    /** The time of occurrence */
    private String time;

    /**
     * Constructs a new Event with the given description and timing of occurrence
     *
     * @param description The task description
     * @param time The time of occurrence
     */
    public Event(String description, String time) {
        super(description, 'E');
        this.time = time;
    }

    public String getEnteredTime() {
        return (time.indexOf(',') == -1)
                ? Parser.reformatDate(time, "d MMM yyyy", "d/M/yyyy")
                : Parser.reformatDateTime(time, "d MMM yyyy, h:mma", "d/M/yyyy h:mma");
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
