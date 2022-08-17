package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

/**
 * ToDoCommand that creates a new Todo and prints message to user
 */
public class ToDoCommand extends AddCommand {

  /**
   * Constructor for TodoCommand
   * @param description Description of Todo to be created
   */
  public ToDoCommand(String description) {
    super(description);
  }

  /**
   * Creates new Todo and prints message to user
   */
  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    ToDo task = new ToDo(this.description);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
