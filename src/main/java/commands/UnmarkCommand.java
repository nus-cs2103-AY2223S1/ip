package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class UnmarkCommand extends Command {

  private int index;

  public UnmarkCommand(String description) throws DukeException {
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
      task.markUndone();
      System.out.println("Aiyah! I've marked this task as not done yet: ");
      System.out.println(task.toString());
    }
  }
}
