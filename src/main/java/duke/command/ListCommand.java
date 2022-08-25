package duke.command;

import java.util.ArrayList;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the listing of tasks of a user.
 */
public class ListCommand extends Command {

  /**
   * Initialises a ListCommand to store the details of the
   * user's input and the TaskList.
   */
  public ListCommand(String[] commandArgs, TaskList tasks) {
    super(commandArgs, tasks);
  }

  /**
   * Lists all the tasks of the user's TaskList.
   * Returns true to indicate that the programme should continue
   * prompting for user input.
   */
  @Override
  public boolean performAction() {
    tasks.list();
    return true;
  }
}
