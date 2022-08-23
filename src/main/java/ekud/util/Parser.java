package ekud.util;

import ekud.exception.EkudException;
import ekud.task.TaskList;
import ekud.task.TaskType;

public class Parser {
  public Parser() {

  }

  public ParseResult parseCommand(String command, TaskList taskList) throws EkudException {
    String firstWord = command.split(" ")[0];
    if (command.equals("bye")) {
        return new ParseResult(true, "Bye. Hope to see you again soon!", false);
    } else if (command.equals("list")) {
        return new ParseResult(false, taskList.printTasks(), false);
    } else if (firstWord.equals("mark")) {
      return new ParseResult(false, taskList.markAsDone(command), true);
    } else if (firstWord.equals("unmark")) {
      return new ParseResult(false, taskList.markAsUndone(command), true);
    } else if (firstWord.equals("todo")) {
      return new ParseResult(false, taskList.addTask(command, TaskType.TODO), true);
    } else if (firstWord.equals("deadline")) {
      return new ParseResult(false, taskList.addTask(command, TaskType.DEADLINE), true);
    } else if (firstWord.equals("event")) {
      return new ParseResult(false, taskList.addTask(command, TaskType.EVENT), true);
    } else if (firstWord.equals("delete")) {
      return new ParseResult(false, taskList.deleteTask(command), true);
    } else {
        throw new EkudException("Invalid command.");
    }
  }

}
