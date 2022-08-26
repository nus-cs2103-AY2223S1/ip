package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.task.Deadline;
import cheese.task.Task;
import cheese.data.TaskList;

public class DeadlineCommand extends Command {
  private String description;
  private String deadline;

  public DeadlineCommand(String description, String deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task addedTask = taskList.add(new Deadline(description, deadline));
    ui.showAddTask(addedTask, taskList.getSize());
    storage.save(taskList);
  }
}
