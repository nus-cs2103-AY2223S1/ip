package commands;
import components.DukeException;
import components.Storage;
import components.TaskList;
import components.Todo;

public class ToDoCommand implements Command {
  @Override
  public String execute(TaskList taskList, Storage storage, String line) throws DukeException {
    if (line.equals("todo")) {
      throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    } else {
      String d1=line.substring(5);
      Todo test = new Todo(d1);
      return taskList.add(test);
    }
  }
}
