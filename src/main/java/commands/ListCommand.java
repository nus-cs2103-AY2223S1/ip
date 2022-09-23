package commands;
import components.DukeException;
import components.Storage;
import components.TaskList;

public class ListCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String line) throws DukeException {
    return taskList.showTasks();
  }
}
