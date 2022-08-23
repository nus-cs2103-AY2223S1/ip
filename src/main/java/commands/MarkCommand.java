package commands;

import exception.*;
import tasks.*;
import main.*;

/**
 * MarkCommand used to mark tasks as done
 */
public class MarkCommand extends Command {

  private int index;

  /**
   * Constuctor for MarkCommand
   *
   * @param description String representation of task number to be marked
   * @throws DukeException if user did not type in a correct task number
   */
  public MarkCommand(String description) throws DukeException {
    try {
      this.index = Integer.parseInt(description);
    } catch (Exception e) {
      throw new DukeException("Invalid tasks");
    }
  }

  /**
   * Marks command and prints out message to users depending on whether the
   * command was successful
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    if (index <= 0 || index > tasks.size()) {
      throw new DukeException("No such tasks found");
    } else {
      Task task = tasks.get(index - 1);
      task.markAsDone();
      storage.save(tasks);
      System.out.println("Fuyoh! I've marked this task as done:");
      System.out.println(task.toString());
    }
  }
}
