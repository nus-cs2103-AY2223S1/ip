package parser;

import command.Command;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.AddCommand;
import command.DeleteCommand;
import command.InvalidCommand;
import exception.DukeException;


import java.util.Objects;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] commands = fullCommand.split(" ", 2);

        if (Objects.equals(commands[0], "bye")) {
            return new ExitCommand();
        } else if (Objects.equals(commands[0], "list")) {
            return new ListCommand();
        } else if (Objects.equals(commands[0], "unmark") || Objects.equals(commands[0], "mark")) {
            try {
                return new MarkCommand(commands[0], commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("An index must be provided for mark or unmark commands.");
            }
        } else if (Objects.equals(commands[0], "deadline") || Objects.equals(commands[0], "event") || Objects.equals(commands[0], "todo")) {
            if (Objects.equals(commands[0], "deadline")) {
                try {
                    String[] taskDetails = commands[1].split(" /by ");
                    return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description or date time of a deadline cannot be empty.");
                }
            } else if (Objects.equals(commands[0], "event")) {
                try {
                    String[] taskDetails = commands[1].split(" /at ");
                    return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description or date time of an event cannot be empty.");
                }
            } else if (Objects.equals(commands[0], "todo")) {
                try {
                    return new AddCommand(commands[0], commands[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            }
        } else if (Objects.equals(commands[0], "delete")) {
            return new DeleteCommand(Integer.parseInt(commands[1]));
        }

        return new InvalidCommand();
    }
}
