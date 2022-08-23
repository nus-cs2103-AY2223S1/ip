package handlers;

import exceptions.DukeException;
import models.TaskList;

public interface DukeCommand {
    public String run(TaskList taskList, String s) throws DukeException;
}
