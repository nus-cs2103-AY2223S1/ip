package cheese.command;

import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.data.TaskList;

/**
 * Represents a user command that is unrecognized.
 */
public class UnknownCommand extends Command {
  /**
   * Executes operation to display error message.
   * 
   * @param {@inheritDoc}
   */
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) {
    ui.showError("Sowwy, I don't understand");
  }
}
