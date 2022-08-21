package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

  private static final Scanner scanner = new Scanner(System.in);

  public static String readCommand() {
    return scanner.nextLine();
  }

  public static Command parse(String fullCommand) throws DukeException {
    String[] inputs = fullCommand.trim().split("\\s+", 2);

    switch (inputs[0]) {
      case "bye":
        return new ExitCommand();
      case "list":
        return new ListCommand();
      case "mark":
        try {
          return (new MarkCommand(Integer.parseInt(inputs[1]) - 1));
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("Invalid input format!");
        } catch (NumberFormatException e) {
          throw new DukeException("Invalid input format!");
        }
      case "unmark":
        try {
          return (new UnmarkCommand(Integer.parseInt(inputs[1]) - 1));
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("Invalid input format!");
        } catch (NumberFormatException e) {
          throw new DukeException("Invalid input format!");
        }
      case "delete":
        try {
          return (new DeleteCommand(Integer.parseInt(inputs[1]) - 1));
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("Invalid input format!");
        } catch (NumberFormatException e) {
          throw new DukeException("Invalid input format!");
        }
      case "todo":
        try {
          return new AddCommand(new Todo(inputs[1], false));
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("Invalid input format!");
        }
      case "deadline":
        try {
          String[] deadlineArgs = inputs[1].split("\\s+/by\\s+", 2);
          return new AddCommand(
            new Deadline(deadlineArgs[0], false, deadlineArgs[1])
          );
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("Invalid input format!");
        } catch (DateTimeParseException e) {
          throw new DukeException("Invalid date format!");
        }
      case "event":
        try {
          String[] eventArgs = inputs[1].split("\\s+/at\\s+", 2);
          return new AddCommand(new Event(eventArgs[0], false, eventArgs[1]));
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("Invalid input format!");
        } catch (DateTimeParseException e) {
          throw new DukeException("Invalid date format!");
        }
      default:
        throw new DukeException(
          "Sorry, I don't know what do you mean by that."
        );
    }
  }
}
