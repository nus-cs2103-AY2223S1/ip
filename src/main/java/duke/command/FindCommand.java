package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

  private String keyword;

  public FindCommand(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    TaskList filtered = taskList.filter(keyword);
    if (filtered.getTaskList().isEmpty()) {
      ui.showOutput("No matching task found!");
    } else {
      ui.showOutput("Here are the matching tasks in your list:");
      ui.showOutput(filtered.toString());
    }
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
