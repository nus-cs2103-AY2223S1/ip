package manmeowth.parser;

import manmeowth.command.AddCommand;
import manmeowth.command.Command;
import manmeowth.command.DeleteCommand;
import manmeowth.command.ExitCommand;
import manmeowth.command.FindCommand;
import manmeowth.command.HelpCommand;
import manmeowth.command.ListCommand;
import manmeowth.command.MarkCommand;
import manmeowth.command.UnmarkCommand;
import manmeowth.exception.ManMeowthException;
import manmeowth.task.TaskId;

/**
 * Interprets user inputted String as Commands.
 *
 * @author WR3nd3
 */
public class Parser {

    /** Commands to interact with manmeowth.ManMeowth program */
    public enum ManMeowthCommand {
        FIND, TODO, EVENT, DEADLINE, MARK, UNMARK, DELETE, LIST, BYE, HELP
    }

    /**
     * Returns valid command for manmeowth.ManMeowth to execute from recognised terms in input.
     *
     * @param input String input from user interacting with manmeowth.ManMeowth.
     * @return command.Command to be executed.
     * @throws ManMeowthException for invalid commands inputted.
     */
    public static Command parse(String input) throws ManMeowthException {
        assert input != null : "Parser :: parse called with null argument";
        // Isolate individual strings in the input
        String[] inputArray = input.split(" ", 2);
        // The command should be the first string
        String cmd = inputArray[0];
        String content = null;

        // Store the second string from the input if any
        if (inputArray.length == 2) {
            content = inputArray[1];
        }

        try {
            ManMeowthCommand c = ManMeowthCommand.valueOf(cmd.toUpperCase());
            switch (c) {
            case FIND:
                return getFindCommand(content);
            case TODO:
                return getToDoCommand(content);
            case EVENT:
                return getEventCommand(content);
            case DEADLINE:
                return getDeadlineCommand(content);
            case MARK:
                return getMarkCommand(content);
            case UNMARK:
                return getUnmarkCommand(content);
            case DELETE:
                return getDeleteCommand(content);
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case HELP:
            default:
                return new HelpCommand();
            }
        } catch (IllegalArgumentException e) {
            throw new ManMeowthException("Nyaat a valid instruction! Try 'help'");
        }
    }

    /**
     * Returns DeleteCommand constructed based on the given input.
     *
     * @param content String entered after given Command.
     * @return DeleteCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static DeleteCommand getDeleteCommand(String content) throws ManMeowthException {
        int position;
        if (content == null) {
            throw new ManMeowthException("Input 'delete x' to delete task x"
                    + " from the list\n");
        }
        position = Integer.parseInt(content);
        return new DeleteCommand(position);
    }

    /**
     * Returns UnmarkCommand constructed based on the given input.
     *
     * @param content String entered after given Command.
     * @return UnmarkCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static UnmarkCommand getUnmarkCommand(String content) throws ManMeowthException {
        int position;
        if (content == null) {
            throw new ManMeowthException("Input 'unmark x' to mark task x"
                    + " as incomplete\n");
        }
        position = Integer.parseInt(content);
        return new UnmarkCommand(position);
    }

    /**
     * Returns MarkCommand constructed based on the given input.
     *
     * @param content String entered after given Command.
     * @return MarkCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static MarkCommand getMarkCommand(String content) throws ManMeowthException {
        int position;
        if (content == null) {
            throw new ManMeowthException("Input 'mark x' to mark task x"
                    + " as complete\n");
        }
        position = Integer.parseInt(content);
        return new MarkCommand(position);
    }

    /**
     * Returns AddCommand constructed based on the given input for a Deadline Task.
     *
     * @param content String entered after given Command.
     * @return AddCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static AddCommand getDeadlineCommand(String content) throws ManMeowthException {
        if (content == null || content.split(" /by ").length != 2) {
            throw new ManMeowthException("Input 'deadline ABC /by DATE' to add "
                    + "deadline ABC due by DATE\n");
        }
        // Split content into deadline description and date
        String[] inputD = content.split(" /by ");
        return new AddCommand(TaskId.D, inputD[0], inputD[1]);
    }

    /**
     * Returns AddCommand constructed based on the given input for an Event Task.
     *
     * @param content String entered after given Command.
     * @return AddCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static AddCommand getEventCommand(String content) throws ManMeowthException {
        if (content == null || content.split(" /at ").length != 2) {
            throw new ManMeowthException("Input 'event ABC /at DATE' to add "
                    + "event ABC on DATE\n");
        }
        // Split content into event description and date
        String[] inputE = content.split(" /at ");
        return new AddCommand(TaskId.E, inputE[0], inputE[1]);
    }

    /**
     * Returns AddCommand constructed based on the given input for a ToDo Task.
     *
     * @param content String entered after given Command.
     * @return AddCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static AddCommand getToDoCommand(String content) throws ManMeowthException {
        if (content == null) {
            throw new ManMeowthException("Input 'todo ABC' to add task ABC\n");
        }
        return new AddCommand(TaskId.T, content);
    }

    /**
     * Returns FindCommand constructed based on the given input.
     *
     * @param content String entered after given Command.
     * @return FindCommand corresponding to given content input.
     * @throws ManMeowthException with respective error message.
     */
    private static FindCommand getFindCommand(String content) throws ManMeowthException {
        if (content == null) {
            throw new ManMeowthException("Input 'find ABC' to find tasks with ABC\n");
        }
        return new FindCommand(content);
    }
}
