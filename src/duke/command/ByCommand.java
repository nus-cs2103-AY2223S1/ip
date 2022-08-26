package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTimeFormatException;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

public class ByCommand extends DataCommand {
    public ByCommand(ParsedData d) {
        super(d);
    }

    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        LocalDateTime dt = Parser.strToDateTime(data.description)
                .orElseThrow(() -> new InvalidTimeFormatException(data.description));
        io.printList(tasks.filterBefore(dt));
    }

}
