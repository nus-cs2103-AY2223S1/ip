package main;

import commands.*;
import exception.*;

/**
 * A parser that parses user input to the commands
 */
public class Parser {

  /**
   * Returns the correct command based on user input
   * @param text The text received from user
   * @throws DukeException
   * @throws Exception
   */
  static Command parse(String text) throws DukeException {
    String[] instructions = text.split(" ", 2);
    String command = instructions[0];

    switch (CommandManager.valueOf(command)) {
      case list:
        return new ListCommand();
      case bye:
        return new ByeCommand();
      case todo:
        return new ToDoTaskCommand(text);
      case deadline:
        return new DeadlineTaskCommand(text);
      case event:
        return new EventTaskCommand(text);
      case mark:
        return new MarkCommand(instructions[1]);
      case unmark:
        return new UnmarkCommand(instructions[1]);
      case delete:
        return new DeleteCommand(instructions[1]);
      default:
        throw new DukeException("Unknown command found");
    }
  }
}
