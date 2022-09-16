package ip.task;

import java.util.Scanner;

import ip.exception.BadTimespan;
import ip.exception.MissingDescription;

/**
 * Encapsulation of an event.
 */
public class Event extends Task {
    /** Timespan of the event. */
    private String timespan;

    /**
     * Constructor to create an event object.
     *
     * @param taskMetadata Contains the description and timespan of the event.
     * @throws MissingDescription If there is no description in options.
     * @throws BadTimespan If the timespan is missing.
     */
    public Event(Scanner taskMetadata) throws MissingDescription, BadTimespan {
        // No event description
        if (!taskMetadata.hasNext()) {
            throw new MissingDescription();
        }
        // Set task metadata to split on " /at "
        taskMetadata.useDelimiter(" /at ");
        String taskDescription = taskMetadata.next().substring(1);
        // No event timespan
        if (!taskMetadata.hasNext()) {
            throw new BadTimespan("");
        }
        String timespan = taskMetadata.next();
        super.setTaskDescription(taskDescription);
        this.timespan = timespan;
    }

    /**
     * Constructor to create event object from formatted string.
     *
     * @param taskMetadata Contains data used to build the event object.
     */
    public Event(String[] taskMetadata) {
        super.setTaskDescription(taskMetadata[2]);
        this.timespan = taskMetadata[3];
        if (taskMetadata[1].equals("true")) {
            super.markComplete();
        }
    }

    public String formatToSave() {
        return "e|" + isComplete + "|" + taskDescription + "|" + timespan + "|\n";
    }

    @Override
    public boolean containsString(String keyword) {
        return super.taskDescription.contains(keyword);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + timespan + ")";
    }
}
