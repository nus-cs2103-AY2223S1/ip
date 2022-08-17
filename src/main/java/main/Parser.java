package main;

import commands.*;
import exception.*;

public class Parser {

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
        case ("add"):
          return new AddCommand(description);
        case ("mark"):
          return new MarkCommand(description);
        case ("unmark"):
          return new UnmarkCommand(description);
        default:
          throw new DukeException("Uknown command received");
      }
    }
  }
}
