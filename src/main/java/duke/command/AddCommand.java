package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {

  private Task task;

  public AddCommand(Task task) {
    this.task = task;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException {
    taskList.add(task);
    ui.showOutput("Task has been added!: " + task.toString());
    ui.showOutput("Total tasks: " + taskList.getTaskList().size());
    storage.save(taskList.getTaskList());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
