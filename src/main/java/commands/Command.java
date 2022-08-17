package commands;

import exception.DukeException;
import java.util.ArrayList;
import tasks.*;

/**
 * Parent class of all commands with single execute method
 */
public abstract class Command {

  public abstract void execute(ArrayList<Task> TaskList) throws DukeException;
}
