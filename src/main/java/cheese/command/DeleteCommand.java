package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.task.Task;
import cheese.data.TaskList;

/**
 * Represents a user command to delete a task.
 */
public class DeleteCommand extends Command {
  /** Index of task to delete using 0-based indexing. */
  private int taskIndex;

  /**
   * Constructs an instance of <code>DeleteCommand</code>.
   * 
   * @param givenIndex Index of task to delete using 1-based indexing.
   */
  public DeleteCommand(int givenIndex) {
    taskIndex = givenIndex - 1;
  }

  /**
   * Executes operations to delete task from list and save the list.
   * 
   * @param {@inheritDoc}
   * @throws CheeseException If given index of task to delete does not match a
   *                         task from <code>taskList</code>.
   */
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task deletedTask = taskList.delete(taskIndex);
    ui.showDeleteTask(deletedTask, taskList.getSize());
    storage.save(taskList);
  }
}
