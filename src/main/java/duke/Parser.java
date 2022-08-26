package duke;

import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ICommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SaveCommand;
import duke.command.UnmarkCommand;

/**
 * Represents a parser to parse string inputs from user
 * and output appropriate commands to be executed.
 */
public class Parser {
    /**
     * Returns appropriate command from parsed input.
     * @param input
     * @return Command from parsed input to be executed.
     */
    public static ICommand parse(String input) {
        Scanner sc = new Scanner(input);
        try {
            String command = sc.next();
            if (!CommandType.COMMAND_MAP.containsKey(command)) {
                throw new DukeException("I'm sorry, but I don't understand that.");
            }
            CommandType type = CommandType.COMMAND_MAP.get(command);
            ICommand cmd = new EmptyCommand();
            switch (type) {
            case TODO:
                String todoBody = sc.nextLine().trim();
                if (todoBody.isBlank()) {
                    throw new DukeException("Something went wrong! Could not read TODO.");
                } else {
                    cmd = new AddCommand(CommandType.TODO, todoBody);
                }
                break;
            case DEADLINE:
                String deadlineBody = sc.nextLine().trim();
                String[] deadlineArgs = deadlineBody.split("\\s/by\\s");
                if (deadlineArgs.length < 2 || deadlineArgs[0].isBlank() || deadlineArgs[1].isBlank()) {
                    throw new DukeException("Something went wrong! Could not read DEADLINE.");
                } else {
                    cmd = new AddCommand(CommandType.DEADLINE, deadlineArgs[0], deadlineArgs[1]);
                }
                break;
            case EVENT:
                String eventBody = sc.nextLine().trim();
                String[] eventArgs = eventBody.split("\\s/at\\s");
                if (eventArgs.length < 2 || eventArgs[0].isBlank() || eventArgs[1].isBlank()) {
                    throw new DukeException("Something went wrong! Could not read DEADLINE.");
                } else {
                    cmd = new AddCommand(CommandType.EVENT, eventArgs[0], eventArgs[1]);
                }
                break;
            case LIST:
                cmd = new ListCommand();
                break;
            case MARK:
                cmd = new MarkCommand(sc.nextInt() - 1);
                break;
            case UNMARK:
                cmd = new UnmarkCommand(sc.nextInt() - 1);
                break;
            case DELETE:
                cmd = new DeleteCommand(sc.nextInt() - 1);
                break;
            case SAVE:
                cmd = new SaveCommand();
                break;
            case FIND:
                if (!sc.hasNext()) {
                    throw new DukeException("Something went wrong! Could not read FIND.");
                } else {
                    cmd = new FindCommand(sc.next());
                }
                break;
            case BYE:
                cmd = new ExitCommand();
                break;
            default:
                throw new DukeException("I'm sorry, but I don't understand that.");
            }
            return cmd;
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
            return new EmptyCommand();
        } finally {
            sc.close();
        }
    }
}
