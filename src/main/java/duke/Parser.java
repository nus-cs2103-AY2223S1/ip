package duke;

import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parser to parse user input and interpret which commands user are inputting.
 */
public class Parser {

    private static int cnt = 0;
    private static Scanner sc = new Scanner(System.in);

    /**
     * Parse input of users and analysing which command is used
     * @param input User input
     * @return Command referred to the user input
     * @throws DukeException If invalid commands or arguments
     */
    public static Command parse(String input) throws DukeException {
        String[] cmd = input.split(" ", 2);
        int num;

        switch (cmd[0]) {
        case "bye":
            return new ExitCommand(cmd[0]);
        case "list":
            return new ListCommand(cmd[0]);
        case "mark":
            if (cmd.length < 2 || cmd[1].length() < 1) {
                throw new DukeException("The index of a task cannot be empty.");
            }
            num = Integer.parseInt(cmd[1]);
            return new MarkCommand(cmd[0], num - 1);
        case "unmark":
            if (cmd.length < 2 || cmd[1].length() < 1) {
                throw new DukeException("The index of a task cannot be empty.");
            }
            num = Integer.parseInt(cmd[1]);
            return new UnmarkCommand(cmd[0], num - 1);
        case "delete":
            if (cmd.length < 2 || cmd[1].length() < 1) {
                throw new DukeException("The index of a task cannot be empty.");
            }

            num = Integer.parseInt(cmd[1]);
            return new DeleteCommand(cmd[0], num - 1);
        default:
            cnt++;
            return new AddCommand(input);
        }
    }
}
