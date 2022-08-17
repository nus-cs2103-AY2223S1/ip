package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class EventCommand extends AddCommand {

  protected String at;

  public EventCommand(String description, String at) {
    super(description);
    this.at = at;
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    Event task = new Event(this.description, this.at);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
