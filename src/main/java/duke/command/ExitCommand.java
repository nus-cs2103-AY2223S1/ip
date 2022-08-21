package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    ui.showOutput("Goodbye!");
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
