package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

  private int index;

  public DeleteCommand(int index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    taskList.delete(index);
    ui.showOutput("Task " + (index + 1) + " has been deleted!");
    storage.save(taskList.getTaskList());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
