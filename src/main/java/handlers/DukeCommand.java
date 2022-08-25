package handlers;

import exceptions.DukeException;
import models.TaskList;

public interface DukeCommand {
    String run(TaskList taskList, String s) throws DukeException;
}
