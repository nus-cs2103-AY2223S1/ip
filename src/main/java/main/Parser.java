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

    if (instructions.length < 2) {
      switch (command) {
        case ("list"):
          return new ListCommand();
        case ("bye"):
          return new ByeCommand();
        default:
          throw new DukeException("Unknown command received");
      }
    } else {
      String description = instructions[1];
      switch (command) {
        case ("todo"):
          return new ToDoCommand(description);
        case ("deadline"):
          String[] deadlinelst = description.split("/by", 2);
          if (deadlinelst.length < 2) {
            throw new DukeException("Alamak, tell me when you need it by...");
          }
          return new DeadlineCommand(deadlinelst[0], deadlinelst[1]);
        case ("event"):
          String[] eventlst = description.split("/at", 2);
          if (eventlst.length < 2) {
            throw new DukeException("Alamak, tell me when the event is...");
          }
          return new EventCommand(eventlst[0], eventlst[1]);
        case ("mark"):
          return new MarkCommand(description);
        case ("unmark"):
          return new UnmarkCommand(description);
        default:
          throw new DukeException("Unknown or incomplete command received");
      }
    }
  }
}
