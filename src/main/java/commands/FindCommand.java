package commands;
import components.DukeException;
import components.Storage;
import components.TaskList;

public class FindCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String input) throws DukeException {
    if (input.equals("find")) {
      throw new DukeException("☹ OOPS!!! The description of find cannot be empty.");
    }
    return taskList.findLine(input.substring(5));
  }
}