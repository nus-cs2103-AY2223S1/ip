package commands;
import components.DukeException;
import components.Storage;
import components.TaskList;

public class UnmarkCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String input) throws DukeException {
    if (input.equals("unmark")) {
      throw new DukeException("☹ OOPS!!! The description of a mark cannot be empty.");
    } else {
      int num = Integer.parseInt(input.substring(7));
      assert num >= 0;
      return taskList.setTaskStatus(num - 1, false);
    }
  }
}