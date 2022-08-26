package duke.task;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.util.ParsedData;
import duke.util.Parser;

public class Event extends Task {
    private static final String SPLIT = "/at ";

    private String period;

    Event(String description, String period) {
        super(description, Parser.strToDateTime(period));
        this.period = period;
    }

    public static Event createEvent(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("event");

        if (data.additionalInfo.length() == 0)
            throw new EmptyTimeException("event", SPLIT);
        return new Event(data.description, data.additionalInfo);
    }

    public static Event createEvent(String description, String period) throws CorruptedLineException {
        if (description.length() == 0 || period.length() == 0)
            throw new CorruptedLineException();

        return new Event(description, period);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), period);
    }

    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(completed ? "Ec" : "Ex", description, period);
    }
}
