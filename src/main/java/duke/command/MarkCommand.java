package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

  private int index;

  public MarkCommand(int index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    taskList.mark(index, true);
    ui.showOutput("Task " + (index + 1) + " is marked as done!");
    storage.save(taskList.getTaskList());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
