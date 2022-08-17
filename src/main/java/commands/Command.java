package commands;

import exception.DukeException;
import java.util.ArrayList;
import main.*;

public abstract class Command {

  public abstract void execute(ArrayList<Task> TaskList) throws DukeException;
}
