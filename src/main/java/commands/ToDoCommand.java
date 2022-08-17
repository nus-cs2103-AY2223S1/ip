package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class ToDoCommand extends AddCommand {

  public ToDoCommand(String description) {
    super(description);
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    ToDo task = new ToDo(this.description);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
