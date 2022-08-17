package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class ByeCommand extends Command {

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    System.out.println("Bye. Hope to see you again soon!");
  }
}
