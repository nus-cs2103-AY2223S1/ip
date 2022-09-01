package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DateTimeException;
import duke.exception.DukeException;
import duke.exception.InputException;
import duke.exception.MarkException;
import duke.exception.TaskException;
import duke.exception.TimeException;

/**
 * duke.Duke utility function to parse through the input from the user, and output the commands to be
 * executed accordingly.
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
        String[] strings = input.split(" ", 2);
        if (strings.length == 1) { //input is guaranteed to not be empty
            return singleCommand(strings);
        } else {
            return doubleCommand(strings);
        }
    }

    private Command singleCommand(String[] arg) throws DukeException {
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
        switch (arg[0]) {
        case "mark":
        case "unmark":
        case "delete":
            if (arg.length == 1) {
                throw new MarkException();
            }
            try {
                int num = Integer.parseInt(arg[1]);
                if (arg[0].equals("mark")) {
                    return new MarkCommand(num);
                } else if (arg[0].equals("unmark")) {
                    return new UnmarkCommand(num);
                } else {
                    return new DeleteCommand(num);
                }
            } catch (NumberFormatException e) {
                throw new MarkException();
            }

        case "deadline":
        case "event":
            if (arg.length == 1 || arg[1].isEmpty()) {
                throw new TaskException();
            } else {
                String[] split = arg[1].split("/", 2);
                if (split.length < 2) {
                    throw new TimeException();
                } else {
                    try {
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

        case "todo":
            if (arg.length == 1) {
                throw new TaskException();
            } else {
                return new TodoCommand(arg[1]);
            }

        case "find":
            if (arg.length == 1) {
                throw new TaskException();
            } else {
                return new FindCommand(arg[1]);
            }

        default:
            throw new InputException();
        }
    }
}
