package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.task.Task;
import cheese.data.TaskList;

public class UnmarkCommand extends Command {
  private int taskIndex;

  public UnmarkCommand(int givenIndex) {
    taskIndex = givenIndex - 1;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task taskNotDone = taskList.markTaskAsNotDone(taskIndex);
    ui.showMarkTaskAsNotDone(taskNotDone);
    storage.save(taskList);
  }
}
