package commands;
import components.DukeException;
import components.Storage;
import components.TaskList;

public class FindCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String input) throws DukeException {
    return taskList.findLine(input.substring(5));
  }
}