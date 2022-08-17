package commands;

import exception.DukeException;
import java.util.ArrayList;
import tasks.*;

public abstract class Command {

  public abstract void execute(ArrayList<Task> TaskList) throws DukeException;
}
