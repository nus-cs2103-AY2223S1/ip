package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.task.Event;
import cheese.task.Task;
import cheese.data.TaskList;

public class EventCommand extends Command {
  private String description;
  private String timeInterval;

  public EventCommand(String description, String timeInterval) {
    this.description = description;
    this.timeInterval = timeInterval;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task addedTask = taskList.add(new Event(description, timeInterval));
    ui.showAddTask(addedTask, taskList.getSize());
    storage.save(taskList);
  }
}
