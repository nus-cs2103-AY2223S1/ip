package duke.task;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.util.ParsedData;
import duke.util.Parser;

/**
 * Task that potentiall have a duration/start time Identified by /at
 */
public class Event extends Task {
    private static final String SPLIT = "/at ";

    private String period;

    Event(String description, String period) {
        super(description, Parser.strToDateTime(period));
        this.period = period;
    }

    /**
     * Creates an Event from ParsedData
     * 
     * @param data ParsedData containing information for both
     * @return Event
     * @throws DukeException Throws when data given is invalid/insufficient
     */
    public static Event createEvent(ParsedData data) throws DukeException {
        if (data.description.length() == 0) {
            throw new EmptyDescriptionException("event");
        }

        if (data.additionalInfo.length() == 0) {
            throw new EmptyTimeException("event", SPLIT);
        }
        return new Event(data.description, data.additionalInfo);
    }

    /**
     * Creates Deadline given the 2 seperate information.
     * 
     * @param description Description of Task
     * @param period When/where its at
     * @return Event
     * @throws CorruptedLineException Throws when data given is invalid/insufficient
     */
    public static Event createEvent(String description, String period) throws CorruptedLineException {
        if (description.length() == 0 || period.length() == 0) {
            throw new CorruptedLineException();
        }

        return new Event(description, period);
    }

    /**
     * {@inheritDoc} Adds [E] tag to identify as event
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), period);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(isComplete ? "Ec" : "Ex", description, period);
    }
}
