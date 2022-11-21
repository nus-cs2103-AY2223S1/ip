package duke.main;

import java.time.DateTimeException;

import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.IncomprehensibleCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UndoCommand;
import duke.command.UnmarkCommand;

/**
 * A class to handle the parser.
 */
public class Parser {

    /**
     * Interprets a string of user input to be use to
     * instruct a particular intended command.
     *
     * @param input a user input to be interpreted
     * @return a Command
     */
    public static Command parse(String input) throws DukeException {
        String[] userInput = input.trim().split(" ", 2);
        switch (userInput[0]) {
        case "bye":
            return exitParse();
        case "list":
            return listParse();
        case "mark":
            return markParse(userInput);
        case "unmark":
            return unmarkParse(userInput);
        case "todo":
            return todoParse(userInput);
        case "deadline":
            return deadlineParse(userInput);
        case "event":
            return eventParse(userInput);
        case "delete":
            return deleteParse(userInput);
        case "date":
            return dateParse(userInput);
        case "find":
            return findParse(userInput);
        case "undo":
            return undoParse();
        default:
            return incomprehensibleCommand();
        }
    }

    private static Command exitParse() {
        return new ExitCommand();
    }

    private static Command listParse() {
        return new ListCommand();
    }

    private static Command markParse(String[] userInput) throws DukeException {
        try {
            return new MarkCommand(Integer.parseInt(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Which one to mark??");
        } catch (NumberFormatException e) {
            throw new DukeException("Can you type a valid number? Don't try to be funny.");
        }
    }

    private static Command unmarkParse(String[] userInput) throws DukeException {
        try {
            return new UnmarkCommand(Integer.parseInt(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Which one to unmark??");
        } catch (NumberFormatException e) {
            throw new DukeException("Can you type a whole number? Don't try to be funny.");
        }
    }

    private static Command todoParse(String[] userInput) throws DukeException {
        try {
            return new ToDoCommand(userInput[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    private static Command deadlineParse(String[] userInput) throws DukeException {
        try {
            String[] thingAndDate = userInput[1].split(" /by ");
            return new DeadlineCommand(thingAndDate[0], thingAndDate[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid input for deadline!");
        }
    }

    private static Command eventParse(String[] userInput) throws DukeException {
        try {
            String[] thingsAndDate = userInput[1].split(" /at ");
            return new EventCommand(thingsAndDate[0], thingsAndDate[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid input for event!");
        }
    }

    private static Command deleteParse(String[] userInput) throws DukeException {
        try {
            return new DeleteCommand(Integer.parseInt(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Delete... delete what??");
        } catch (NumberFormatException e) {
            throw new DukeException("Can you type a valid number?");
        }
    }

    private static Command dateParse(String[] userInput) throws DukeException {
        try {
            return new DateCommand(userInput[1]);
        } catch (DateTimeException e) {
            throw new DukeException("Key in a valid date!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Which date?? Please specify!");
        }
    }

    private static Command findParse(String[] userInput) throws DukeException {
        try {
            return new FindCommand(userInput[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("What do you want me to find for you...?");
        }
    }

    private static Command incomprehensibleCommand() {
        return new IncomprehensibleCommand();
    }

    private static Command undoParse() {
        return new UndoCommand();
    }
}
