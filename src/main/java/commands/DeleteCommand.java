package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

/**
 * DeleteCommand used to delete tasks
 */
public class DeleteCommand extends Command {

  private int index;

  /**
   * Constuctor for DeleteCommand
   *
   * @param description String representation of task number to be marked
   * @throws DukeException if user did not type in a correct task number
   */
  public DeleteCommand(String description) throws DukeException {
    try {
      this.index = Integer.parseInt(description);
    } catch (Exception e) {
      throw new DukeException("Invalid tasks");
    }
  }

  /**
   * Marks command and prints out message to users depending on whether the command was successful
   */
  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    if (index <= 0 || index > tasklist.size()) {
      throw new DukeException("No such task found");
    } else {
      Task task = tasklist.remove(index - 1);
      System.out.println("Noted. I've removed this task:");
      System.out.println(task.toString());
      System.out.println(
        "Now you have " + tasklist.size() + " task(s) in the list"
      );
    }
  }
}
