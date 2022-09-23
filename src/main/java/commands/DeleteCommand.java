package commands;
import components.Deadline;
import components.DukeException;
import components.Storage;
import components.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeleteCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String input) throws DukeException {
    if (input.equals("delete")) {
      throw new DukeException(
          "☹ OOPS!!! The description of a delete cannot be empty.");
    } else {
      int removal = Integer.parseInt(input.substring(7));
      return taskList.remove(removal - 1);
    }
  }
}