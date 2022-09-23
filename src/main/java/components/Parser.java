package components;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.StorageParser;
import commands.ToDoCommand;
import commands.UnmarkCommand;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser, which tries to understand user input.
 */
public class Parser {

  private static Ui Ui;
  private static TaskList taskList;
  private static Storage storage;

  /**
   * Connects Ui object with Parser.
   *
   * @param ui Ui to be connected with Parser.
   */
  public static void setUi(Ui ui) {
    Parser.Ui = ui;
  }

  /**
   * Connects TaskList object with Parser.
   *
   * @param taskList taskList to be connected with Parser.
   */
  public static void setTaskList(TaskList taskList) {
    Parser.taskList = taskList;
  }

  /**
   * Parses and trying to make sense of user input.
   *
   * @param line string representation of user input.
   * @throws DukeException If user input is incoherent.
   */
  public static Command readCommands(String line) throws DukeException {
    if (line.equals("bye")) {
      System.out.println("Bye. Hope to see you again soon!");
      System.exit(0);
    } else if (line.equals("list")) {
      return new ListCommand();
    } else if (line.contains("unmark")) {
      return new UnmarkCommand();
    } else if (line.contains("mark")) {
      return new MarkCommand();
    } else if (line.contains("find")) {
      return new FindCommand();
    } else if (line.contains("todo")) {
      return new ToDoCommand();
    } else if (line.contains("deadline")) {
      return new DeadlineCommand();
    } else if (line.contains("event")) {
      return new EventCommand();
    } else if (line.contains("delete")) {
      return new DeleteCommand();
    } else {
      throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
    }
    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
  }

  public static void parseLineStorage(String line, Storage storage) {
    if (line.contains("[T]")) {
      StorageParser.parseToDo(line, storage);
    } else if (line.contains("[D]")) {
      StorageParser.parseDeadline(line, storage);
    } else if (line.contains("[E]")) {
      StorageParser.parseEvent(line, storage);
    }
  }
}


