package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    ui.showOutput(taskList.toString());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
