package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

public interface Command {
    void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException;
}
