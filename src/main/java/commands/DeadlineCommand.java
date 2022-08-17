package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class DeadlineCommand extends AddCommand {

  private String by;

  public DeadlineCommand(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    Deadline task = new Deadline(this.description, this.by);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
