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
   * @return The correct command based on user input
   * @throws DukeException Unknown or incomplete command message if user input does not qualify
   */
  static Command parse(String text) throws DukeException {
    String[] instructions = text.split(" ", 2);
    String command = instructions[0];
    switch (command) {
      case ("list"):
        return new ListCommand();
      case ("bye"):
        return new ByeCommand();
      case ("todo"):
        return new ToDoCommand(text);
      case ("deadline"):
        return new DeadlineCommand(text);
      case ("event"):
        return new EventCommand(text);
      case ("mark"):
        return new MarkCommand(instructions[1]);
      case ("unmark"):
        return new UnmarkCommand(instructions[1]);
      default:
        throw new DukeException("Unknown command received");
    }
  }
}
