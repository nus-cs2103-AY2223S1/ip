package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.data.TaskList;

/**
 * Represents a user command.
 */
public abstract class Command {
  /**
   * Executes operations relating to the command.
   * 
   * @param taskList Task list.
   * @param storage  Storage to interact with the save file.
   * @param ui       User interface to interact with the user.
   * @throws CheeseException
   */
  public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException;
}