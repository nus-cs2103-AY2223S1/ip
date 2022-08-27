package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.TaskId;

/**
 * Interprets user inputted String as Commands.
 *
 * @author WR3nd3
 */
public class Parser {

    /** Commands to interact with duke.Duke program */
    public enum DukeCommand {
        FIND, TODO, EVENT, DEADLINE, MARK, UNMARK, DELETE, LIST, BYE
    }

    /**
     * Returns valid command for duke.Duke to execute from recognised terms in input.
     *
     * @param input String input from user interacting with duke.Duke.
     * @return command.Command to be executed.
     * @throws DukeException for invalid commands inputted.
     */
    public static Command parse(String input) throws DukeException{
        // Isolate individual strings in the input
        String[] inputArray = input.split(" ", 2);
        // The command should be the first string
        String cmd = inputArray[0];
        String content = null;
        int position;

        // Store the second string from the input if any
        if (inputArray.length == 2) {
            content = inputArray[1];
        }

        try {
            DukeCommand c = DukeCommand.valueOf(cmd.toUpperCase());
            switch (c) {
            case FIND:
                if (content == null) {
                    throw new DukeException("Input 'find ABC' to find tasks with ABC\n");
                }
                return new FindCommand(content);
            case TODO:
                if (content == null) {
                    throw new DukeException("Input 'todo ABC' to add task ABC\n");
                }
                return new AddCommand(TaskId.T, content);
            case EVENT:
                if (content == null || content.split(" /at ").length != 2) {
                    throw new DukeException("Input 'event ABC /at DATE' to add "
                            + "event ABC on DATE\n");
                }
                // Split content into event description and date
                String[] inputE = content.split(" /at ");
                return new AddCommand(TaskId.E, inputE[0], inputE[1]);
            case DEADLINE:
                if (content == null || content.split(" /by ").length != 2) {
                    throw new DukeException("Input 'deadline ABC /by DATE' to add "
                            + "deadline ABC due by DATE\n");
                }
                // Split content into deadline description and date
                String[] inputD = content.split(" /by ");
                return new AddCommand(TaskId.D, inputD[0], inputD[1]);
            case MARK:
                if (content == null) {
                    throw new DukeException("Input 'mark x' to mark task x"
                            + " as complete\n");
                }
                position = Integer.parseInt(content);
                return new MarkCommand(position);
            case UNMARK:
                if (content == null) {
                    throw new DukeException("Input 'unmark x' to mark task x"
                            + " as incomplete\n");
                }
                position = Integer.parseInt(content);
                return new UnmarkCommand(position);
            case DELETE:
                if (content == null) {
                    throw new DukeException("Input 'delete x' to delete task x"
                            + " from the list\n");
                }
                position = Integer.parseInt(content);
                return new DeleteCommand(position);
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeException();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException();
        }
    }
}
