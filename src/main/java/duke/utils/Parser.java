package duke.utils;

import duke.Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DateTimeException;
import duke.exception.DukeException;
import duke.exception.InputException;
import duke.exception.OutOfRangeException;
import duke.exception.TaskException;
import duke.exception.TimeException;

/**
 * duke.Duke utility function to parse through the input from the user, and output the commands to
 * be executed accordingly.
 */
public class Parser {

    /**
     * Takes in the input from the user and generates the associated Command accordingly.
     *
     * @param input String input from the user.
     * @return Command task
     * @throws DukeException If input is not of the specified formats.
     * @see duke.command.Command
     * @see duke.exception.DukeException
     */
    public Command parse(String input) throws DukeException {
        String[] commandLength = input.split(" ", 2);

        // Ensures that the command deciding word is case-insensitive.
        commandLength[0] = commandLength[0].toLowerCase();

        // Input is guaranteed to not be empty.
        if (commandLength.length == 1) {
            return singleCommand(commandLength);
        } else {
            return doubleCommand(commandLength);
        }
    }

    private Command singleCommand(String[] arg) throws DukeException {
        assert arg.length == 1;
        switch (arg[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
            throw new InputException();
        }
    }

    private Command doubleCommand(String[] arg) throws DukeException {
        assert arg.length == 2;
        switch (arg[0]) {
        case "mark":
        case "unmark":
        case "delete":
        case "edit":
            return numberCommand(arg);
        case "deadline":
        case "event":
            return timeCommand(arg);
        case "todo":
        case "find":
            return descriptionOnlyCommand(arg);
        default:
            throw new InputException();
        }
    }

    private Command numberCommand(String[] arg) throws DukeException {
        try {
            String command = arg[0];
            arg = arg[1].split(" ", 2);
            int num = Integer.parseInt(arg[0]);
            switch(command) {
            case "mark":
                return new MarkCommand(num);
            case "unmark":
                return new UnmarkCommand(num);
            case "edit":
                return new EditCommand(num,arg[1]);
            case "delete":
                return new DeleteCommand(num);
            default:
                throw new InputException();
            }
        } catch (NumberFormatException e) {
            throw new OutOfRangeException();
        }
    }

    private Command timeCommand(String[] arg) throws DukeException {
        String[] split = arg[1].split("/", 2);
        if (split.length < 2) {
            throw new TimeException();
        } else {
            try {
                // Adds a default time so that no error is thrown if user does not input time.
                if (split[1].substring(3).length() < 11) {
                    split[1] = split[1] + " 23:59";
                }
                LocalDateTime time = LocalDateTime.parse(split[1].substring(3),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                if (arg[0].equals("deadline")) {
                    return new DeadlineCommand(split[0], time);
                } else {
                    return new EventCommand(split[0], time);
                }
            } catch (DateTimeParseException e) {
                throw new DateTimeException();
            }
        }
    }

    private Command descriptionOnlyCommand(String[] arg) {
        if (arg[0].equals("todo")) {
            return new TodoCommand(arg[1]);
        } else {
            return new FindCommand(arg[1]);
        }
    }
}
