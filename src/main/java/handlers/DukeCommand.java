package handlers;

import exceptions.DukeException;
import models.Task;

import java.util.List;

public interface DukeCommand {
    public String run(List<Task> taskList, String s) throws DukeException;
}
