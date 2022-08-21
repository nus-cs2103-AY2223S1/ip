package duke.command;

import duke.*;

/**
 * Loads the tasks from the file.
 */
public class LoadCommand extends Command{
  /**
   * Executes the load command.
   * @param ui the user interface
   * @param storageList the storage list
   * @throws DukeException if the command is invalid
   */
  @Override
  public void execute(Ui ui, StorageList storageList) throws DukeException {
    String filename = Duke.getDefaultFileName();
    String input = ui.getLastInput();
    if (input.split(" ").length == 2) {
      filename = input.split(" ")[1];
    }

    FileIO.load(storageList, filename);
    Output.LOAD.print();
  }
}
