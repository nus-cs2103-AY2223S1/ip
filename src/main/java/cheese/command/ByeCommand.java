package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Ui;

/**
 * Represents a user command to exit the program.
 */
public class ByeCommand extends Command {
  /**
   * Executes operation to display goodbye message.
   * 
   * @param {@inheritDoc}
   */
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) {
    ui.showGoodbye();
  }

  /**
   * Checks if given command is a <code>ByeCommand</code>.
   * 
   * @param command Instance of <code>Command</code>.
   * @return True, if command is a <code>ByeCommand</code>. False otherwise.
   */
  public static boolean isBye(Command command) {
    return command instanceof ByeCommand;
  }
}
