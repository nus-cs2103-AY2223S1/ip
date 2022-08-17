package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class MarkCommand extends Command {

  private int index;

  public MarkCommand(String description) throws DukeException {
    try {
      this.index = Integer.parseInt(description);
    } catch (Exception e) {
      throw new DukeException("Invalid tasks");
    }
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    if (index <= 0 || index > tasklist.size()) {
      throw new DukeException("No such tasks found");
    } else {
      Task task = tasklist.get(index - 1);
      task.markAsDone();
      System.out.println("Fuyoh! I've marked this task as done:");
      System.out.println(task.toString());
    }
  }
}
