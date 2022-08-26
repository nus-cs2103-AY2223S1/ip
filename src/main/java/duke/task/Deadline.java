package duke.task;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.util.ParsedData;
import duke.util.Parser;

/**
 * Task that potentiall have a time deadline Identified by /by
 */
public class Deadline extends Task {
    private static final String SPLIT = "/by ";

    private String deadline;

    private Deadline(String description, String deadline) {
        super(description, Parser.strToDateTime(deadline));
        this.deadline = deadline;
    }

    /**
     * Creates Deadline from ParsedData
     * 
     * @param data ParsedData containing information for both
     * @return Deadline
     * @throws DukeException Throws when data given is invalid/insufficient
     */
    public static Deadline createDeadline(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("deadline");

        if (data.additionalInfo.length() == 0)
            throw new EmptyTimeException("deadline", SPLIT);

        return new Deadline(data.description, data.additionalInfo);
    }

    /**
     * Creates Deadline given the 2 seperate information. Used when reading from file.
     * 
     * @param description Description of Task
     * @param deadline When/period where its due
     * @return Deadline
     * @throws CorruptedLineException Throws when there is missing fields
     */
    public static Deadline createDeadline(String description, String deadline) throws CorruptedLineException {
        if (description.length() == 0 || deadline.length() == 0)
            throw new CorruptedLineException();

        return new Deadline(description, deadline);
    }

    /**
     * {@inheritDoc} Adds [D] Identifier for deadline
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(completed ? "Dc" : "Dx", description, deadline);
    }
}
