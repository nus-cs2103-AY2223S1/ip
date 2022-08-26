package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.task.Task;
import cheese.data.TaskList;

/**
 * Represents a user command to unmark a task.
 */
public class UnmarkCommand extends Command {
  /** Index of task to unmark using 0-based indexing. */
  private int taskIndex;

  /**
   * Constructs an instance of <code>UnmarkCommand</code>.
   * 
   * @param givenIndex Index of task to unmark using 1-based indexing.
   */
  public UnmarkCommand(int givenIndex) {
    taskIndex = givenIndex - 1;
  }

  /**
   * Executes operations to unmark task and save the list.
   * 
   * @param {@inheritDoc}
   * @throws CheeseException If given index of task to unmark does not match a
   *                         task from <code>taskList</code>.
   */
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task taskNotDone = taskList.markTaskAsNotDone(taskIndex);
    ui.showMarkTaskAsNotDone(taskNotDone);
    storage.save(taskList);
  }
}
