package commands;
import components.Deadline;
import components.DukeException;
import components.Storage;
import components.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String input) throws DukeException {
    if (input.equals("deadline")) {
     throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
    } else {
      try {
        String description = input.substring(9, input.indexOf("/") - 1);
        String var = input.substring(input.indexOf("/") + 4, input.length());
        Deadline test = new Deadline(description, LocalDate.parse(var));
        return taskList.add(test);
      } catch (DateTimeParseException e) {
        String description = input.substring(9, input.indexOf("/") - 1);
        Deadline test = new Deadline(description, input.substring(input.indexOf("/") + 4, input.length()));
        return taskList.add(test);
      }
    }
  }
}