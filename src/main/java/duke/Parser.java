package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parser to parse user input and interpret which commands user are inputting.
 */
public class Parser {

    /**
     * Parse input of users and analysing which command is used
     * @param input User input
     * @return Command referred to the user input
     * @throws DukeException If invalid commands or arguments
     */
    public static Command parse(String input) {
        String[] cmd = input.split(" ", 2);
        int index;

        try {
            switch (cmd[0]) {
            case "bye":
                return new ExitCommand(cmd[0]);
            case "list":
                return new ListCommand(cmd[0]);
            case "mark":
                if (cmd.length != 2 || cmd[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }
                index = Integer.parseInt(cmd[1]);
                return new MarkCommand(cmd[0], index - 1);
            case "unmark":
                if (cmd.length != 2 || cmd[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }
                index = Integer.parseInt(cmd[1]);
                return new UnmarkCommand(cmd[0], index - 1);
            case "delete":
                if (cmd.length != 2 || cmd[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }
                index = Integer.parseInt(cmd[1]);
                return new DeleteCommand(cmd[0], index - 1);
            case "find":
                if (cmd.length != 2 || cmd[1].length() < 1) {
                    throw new DukeException("The keyword of a task cannot be empty.");
                }
                return new FindCommand(cmd[0], cmd[1]);
            default:
                return new AddCommand(input);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
