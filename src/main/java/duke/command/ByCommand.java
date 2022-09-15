package duke.command;

import java.time.LocalDateTime;

import duke.exceptions.InvalidTimeFormatException;
import duke.inputoutput.DukeIo;
import duke.util.DataParser;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command that filters current tasks and display tasks that is smaller than
 * the input date
 */
public class ByCommand extends DataCommand {

    /**
     * Takes in ParsedData potentially containing a datetime pattern.
     *
     * @param parsedData ParsedData containing a possible datetime pattern
     */
    public ByCommand(ParsedData parsedData) {
        super(parsedData);
    }

    /**
     * {@inheritDoc} Prints all task (with datetime assigned) that is before the
     * specified date.
     *
     * @throws InvalidTimeFormatException raised if no datetime format is detected
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage, CommandSelector cs)
            throws InvalidTimeFormatException {
        LocalDateTime dt = DataParser.strToDateTime(data.description)
                .orElseThrow(() -> new InvalidTimeFormatException(data.description));

        io.printList(tasks.getTaskBefore(dt));
    }

}
