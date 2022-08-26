package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand implements Command {
  private final String index;

  public UnmarkCommand(String index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
    ui.showToUser(itemList.unmark(Integer.parseInt(index)));
  }
}
