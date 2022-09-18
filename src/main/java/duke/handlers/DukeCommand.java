package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

public interface DukeCommand {
    DukeResponse run(TaskList taskList, String s) throws DukeException;
}
