package duke.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.InvalidTimeFormatException;
import duke.task.Task;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command that filters current tasks and display tasks that is smaller than the input date
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
     * {@inheritDoc} Prints all task (with datetime assigned) that is before the specified date.
     * 
     * @throws InvalidTimeFormatException raised if no datetime format is detected
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws InvalidTimeFormatException {
        LocalDateTime dt = Parser.strToDateTime(data.description)
                .orElseThrow(() -> new InvalidTimeFormatException(data.description));

        List<Task> cpy = new ArrayList<>(tasks.getTasks());
        cpy.sort(null);
        List<Task> ret = new ArrayList<>();
        for (Task t : cpy) {
            if (t.compareTo(dt) > 0) {
                break;
            }
            ret.add(t);
        }

        io.printList(ret);
    }

}
