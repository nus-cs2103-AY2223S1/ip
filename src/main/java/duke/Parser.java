package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Parser helps
 */
public class Parser {

    static final int TLENGTH = 5;
    static final int ELENGTH = 6;
    static final int DLENGTH = 9;
    static final int FIND_LENGTH = 5;
    static final int MARK_LENGTH = 5;
    static final int UNMARK_LENGTH = 7;
    static final int DEL_LENGTH = 7;
    static final String EXIT_SYNTAX = "bye";
    static final String LIST_SYNTAX = "list";
    static final String DELETE_SYNTAX = "delete";
    static final String MARK_SYNTAX = "mark";
    static final String UNMARK_SYNTAX = "unmark";
    static final String[] ADD_COMMANDS = {"todo", "event", "deadline"};
    static final String FIND_SYNTAX = "find";

    private static String getFirstWord(String input) {
        return input.split(" ")[0].toLowerCase();
    }


    private static boolean isAddTodoCommand(String input) {
        return getFirstWord(input).equals("todo");
    }

    private static boolean isAddEventCommand(String input) {
        return getFirstWord(input).equals("event");
    }

    private static boolean isAddDeadlineCommand(String input) {
        return getFirstWord(input).equals("deadline");
    }

    /**
     * @param fullCommand input Command from the user.
     * @return Task object
     */
    public static Task commandToTask(String fullCommand) throws DukeException {
        if (isAddTodoCommand(fullCommand)) {
            String desc = fullCommand.substring(TLENGTH);
            return new Todo(desc);
        } else if (isAddEventCommand(fullCommand)) {
            String[] commandParts = fullCommand.substring(ELENGTH).split(" /at ");
            if (commandParts.length != 2) {
                throw new DukeException("Give the right syntax");
            }
            String desc = commandParts[0];
            String time = commandParts[1];
            return new Event(desc, time);
        } else if (isAddDeadlineCommand(fullCommand)) {
            String[] commandParts = fullCommand.substring(ELENGTH).split(" /by ");
            if (commandParts.length != 2) {
                throw new DukeException("Give the right syntax");
            }
            String desc = commandParts[0];
            String time = commandParts[1];
            return new Deadline(desc, time);
        } else {
            throw new DukeException("not planned task parser");
        }
    }

    private static boolean isAddCommand(String fullCommand) {
        for (String addCommands : ADD_COMMANDS) {
            if (getFirstWord(fullCommand).toLowerCase().contains(addCommands)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param fullCommand input Command from the user.
     * @return Command to be executed .
     * @throws DukeException When the command does not parse properly.
     */
    public static Command parse(String fullCommand) throws DukeException {
        // DO NOT USE ARROW SWTICH AS IT IS A JAVA12 FEATURE
        if (isAddCommand(fullCommand)) {
            return new AddCommand(fullCommand);
        }
        switch (getFirstWord(fullCommand)) {
        case FIND_SYNTAX:
            return new FindCommand(fullCommand.substring(FIND_LENGTH));
        case LIST_SYNTAX:
            return new ListCommand();
        case EXIT_SYNTAX:
            return new ExitCommand();
        case DELETE_SYNTAX:
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(DEL_LENGTH)));
        case MARK_SYNTAX:
            return new MarkCommand(Integer.parseInt(fullCommand.substring(MARK_LENGTH)));
        case UNMARK_SYNTAX:
            return new UnmarkCommand(Integer.parseInt(fullCommand.substring(UNMARK_LENGTH)));
        default:
            throw new DukeException("Parsing error");
        }
    }

}
