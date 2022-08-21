package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

  private int index;

  public UnmarkCommand(int index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    taskList.mark(index, false);
    ui.showOutput("Task " + (index + 1) + " is marked as not done!");
    storage.save(taskList.getTaskList());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
