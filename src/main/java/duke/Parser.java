package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates parsing of user commands.
 */
public class Parser {
    /**
     * Parses user input and produces the necessary actions to take.
     * @param s User input string
     * @return Command
     * @throws DukeException Invalid user input
     */
    public static Command parse(String s) throws DukeException {
        assert (s != null);
        String[] userInput = s.trim().split(" ", 2);

        try {
            switch (userInput[0]) {
            case "bye":
                return processGoodbye(userInput);

            case "list":
                return processList(userInput);

            case "todo":
                return processTodo(userInput);

            case "event":
                return processEvent(userInput);

            case "deadline":
                return processDeadline(userInput);

            case "mark":
                return processMark(userInput);

            case "unmark":
                return processUnmark(userInput);

            case "delete":
                return processDelete(userInput);

            case "find":
                return processFind(userInput);

            case "help":
                return processHelp(userInput);


            default:
                throw new DukeException("Unrecognized command.");
            }

        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid date format. Type in \"yyyy-mm-dd HHmm\" format.");
        } catch (NumberFormatException err) {
            throw new DukeException("Wrong argument passed to mark/unmark. Type in a number.");
        } catch (DukeException err) {
            throw err;
        }
    }

    private static Command processGoodbye(String[] userInput) throws DukeException {
        if (userInput.length > 1) {
            throw new DukeException("Unrecognized command.");
        } else {
            return new GoodbyeCommand();
        }
    }

    private static Command processList(String[] userInput) throws DukeException {
        if (userInput.length > 1) {
            throw new DukeException("Unrecognized command.");
        } else {
            return new ListCommand();
        }
    }

    private static Command processTodo(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing todo description.");
        } else {
            return new ToDoCommand(userInput[1].trim());
        }
    }

    private static Command processEvent(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing event description.");
        }

        String[] tmp = userInput[1].trim().split("/at");
        if (tmp.length < 2) {
            throw new DukeException("Missing /at tag or date for event.");
        }

        return new EventCommand(
                tmp[0].trim(),
                LocalDateTime.parse(
                        tmp[1].trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    private static Command processDeadline(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing deadline description.");
        }

        String[] tmp = userInput[1].trim().split("/by");
        if (tmp.length < 2) {
            throw new DukeException("Missing /by tag or date for deadline.");
        }

        return new DeadlineCommand(
                tmp[0].trim(),
                LocalDateTime.parse(
                        tmp[1].trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    private static Command processMark(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing task to mark.");
        } else {
            int tmp = Integer.parseInt(userInput[1]);
            return new MarkCommand(tmp);
        }
    }

    private static Command processUnmark(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing task to unmark.");
        } else {
            int tmp = Integer.parseInt(userInput[1]);
            return new UnmarkCommand(tmp);
        }
    }

    private static Command processDelete(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing task to delete.");
        } else {
            int tmp = Integer.parseInt(userInput[1]);
            return new DeleteCommand(tmp);
        }
    }

    private static Command processFind(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException("Missing task keyword to find.");
        } else {
            return new FindCommand(userInput[1].trim());
        }
    }

    private static Command processHelp(String[] userInput) throws DukeException {
        if (userInput.length > 1) {
            throw new DukeException("Unrecognized command.");
        } else {
            return new HelpCommand();
        }
    }
}
