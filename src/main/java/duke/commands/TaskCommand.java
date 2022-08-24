package duke.commands;

import duke.exception.*;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.*;
/**
 * AddCommand class is the parent class for TodoTask, DeadlineTask and EventTask
 */
public class TaskCommand extends Command {

  protected String description;

  public TaskCommand(String description) throws DukeException {
    String[] addlst = description.split(" ", 2);
    if (addlst.length < 2) {
      throw new DukeException("Task description missing!");
    }
    this.description = addlst[1];
  }

  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
  };

  /**
   * Prints the correct message depending on the tasks type
   * 
   * @param tasklist Array of tasks
   * @param task     The task that has been added
   */
  public void printMessage(TaskList tasklist, Task task) {
    System.out.println("Got it. I've added this task: ");
    System.out.println(task.toString());
    System.out.println(
        "Now you have " + tasklist.size() + " task(s) in the list");
  }
}
