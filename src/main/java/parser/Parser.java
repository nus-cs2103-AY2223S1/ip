package parser;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ToDoCommand;
import exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String command, description;

        String[] words = input.split(" ", 2);
        command = words[0];
        if (words.length > 1) {
            description = words[1];
        } else {
            description = "";
        }

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "todo": {
            if (description.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new ToDoCommand(description);
        }
        case "deadline": {
            String[] splitArgs = description.split(" /by ", 2);
            if (splitArgs.length < 2) {
                throw new DukeException("Invalid format for deadline.");
            }
            String title = splitArgs[0];
            String stringBy = splitArgs[1];
            try {
                LocalDate dateBy = LocalDate.parse(stringBy);
                return new DeadlineCommand(title, dateBy);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format.");
            }
        }
        case "event": {
            String[] splitArgs = description.split(" /at ", 2);
            if (splitArgs.length < 2) {
                throw new DukeException("Invalid format for event.");
            }
            String title = splitArgs[0];
            String stringAt = splitArgs[1];
            try {
                LocalDate dateAt = LocalDate.parse(stringAt);
                return new EventCommand(title, dateAt);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format.");
            }
        }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
