package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.TaskList;

public interface DukeCommand {
    String run(TaskList taskList, String s) throws DukeException;
}
