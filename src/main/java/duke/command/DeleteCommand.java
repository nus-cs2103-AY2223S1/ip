package duke.command;

import duke.*;
import duke.task.Task;

/**
 * Delete a specified Task from the StorageList.
 */
public class DeleteCommand extends Command {
  /**
   * Executes the delete command.
   * @param ui the user interface
   * @param storageList the storage list
   * @throws DukeException if the command is invalid
   */
  @Override
  public void execute(Ui ui, StorageList storageList) throws DukeException {
    int index = Parser.getIndex(ui.getLastInput());
    Task task = storageList.get(index);
    storageList.delete(index);
    Output.DELETE.modifyTask(task, storageList);
  }
}
