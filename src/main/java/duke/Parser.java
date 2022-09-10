package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SnoozeCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parser to parse user input and interpret which commands user are inputting.
 */
public class Parser {

    /**
     * Parse input of users and analysing which command is used.
     *
     * @param inputs User input
     * @return Command referred to the user input
     */
    public static Command parse(String... inputs) {
        assert(inputs != null);
        String input = inputs[0];
        String[] commands = input.split(" ", 2);
        int index;

        try {
            switch (commands[0]) {
            case "bye":
                return new ExitCommand(commands[0]);
            case "list":
                return new ListCommand(commands[0]);
            case "mark":
                if (commands.length != 2 || commands[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }
                index = Integer.parseInt(commands[1]);
                return new MarkCommand(commands[0], index - 1);
            case "unmark":
                if (commands.length != 2 || commands[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }
                index = Integer.parseInt(commands[1]);
                return new UnmarkCommand(commands[0], index - 1);
            case "delete":
                if (commands.length != 2 || commands[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }
                index = Integer.parseInt(commands[1]);
                return new DeleteCommand(commands[0], index - 1);
            case "find":
                if (commands.length != 2 || commands[1].length() < 1) {
                    throw new DukeException("The keyword of a task cannot be empty.");
                }
                return new FindCommand(commands[0], commands[1]);
            case "snooze":
                if (commands.length != 2 || commands[1].length() < 1) {
                    throw new DukeException("The information to snooze a task cannot be empty.");
                }
                String[] snoozeInfo = commands[1].split(" ", 2);
                index = Integer.parseInt(snoozeInfo[0]);
                String newDate = snoozeInfo[1];
                return new SnoozeCommand(commands[0], index - 1, newDate);
            default:
                return new AddCommand(input);
            }
        } catch (DukeException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
