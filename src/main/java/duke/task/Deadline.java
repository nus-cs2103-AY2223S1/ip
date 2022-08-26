package duke.task;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.util.ParsedData;
import duke.util.Parser;

public class Deadline extends Task {
    private static final String SPLIT = "/by ";

    private String deadline;

    private Deadline(String description, String deadline) {
        super(description, Parser.strToDateTime(deadline));
        this.deadline = deadline;
    }

    public static Deadline createDeadline(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("deadline");

        if (data.additionalInfo.length() == 0)
            throw new EmptyTimeException("deadline", SPLIT);

        return new Deadline(data.description, data.additionalInfo);
    }

    public static Deadline createDeadline(String description, String deadline) throws CorruptedLineException {
        if (description.length() == 0 || deadline.length() == 0)
            throw new CorruptedLineException();

        return new Deadline(description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(completed ? "Dc" : "Dx", description, deadline);
    }
}
