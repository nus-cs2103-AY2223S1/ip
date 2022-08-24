package duke.commands;

import duke.exception.*;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.*;

/**
 * DeleteCommand used to delete tasks
 */
public class DeleteCommand extends Command {

  private int index;

  /**
   * Constructor for DeleteCommand
   *
   * @param description String representation of task number to be deleted
   * @throws DukeException if user did not type in a correct task number
   */
  public DeleteCommand(String description) throws DukeException {
    try {
      description = description.split(" ")[1];
      this.index = Integer.parseInt(description);
    } catch (Exception e) {
      throw new DukeException("Invalid tasks");
    }
  }

  /**
   * Deletes tasks and prints out message to users regarding task that has been deleted
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    if (index <= 0 || index > tasks.size()) {
      throw new DukeException("No such task found");
    } else {

      Task task = tasks.remove(index - 1);
      storage.save(tasks);
      System.out.println("Noted. I've removed this task:");
      System.out.println(task.toString());
      System.out.println(
          "Now you have " + tasks.size() + " task(s) in the list");
    }
  }
}
