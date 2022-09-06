package duke;

import java.util.HashSet;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ICommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SaveCommand;
import duke.command.UnmarkCommand;
import duke.command.WrongCommand;

/**
 * Represents a parser to parse string inputs from user
 * and output appropriate commands to be executed.
 */
public class Parser {
    /**
     * Returns appropriate command from parsed input.
     *
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
            switch (type) {
            case TODO:
                String todoBody = sc.nextLine().trim();
                if (todoBody.isBlank()) {
                    throw new DukeException("Something went wrong! Could not read TODO.");
                }
                return new AddCommand(CommandType.TODO, todoBody);
            case DEADLINE:
                String deadlineBody = sc.nextLine().trim();
                String[] deadlineArgs = deadlineBody.split("\\s/by\\s");
                if (deadlineArgs.length < 2 || deadlineArgs[0].isBlank() || deadlineArgs[1].isBlank()) {
                    throw new DukeException("Something went wrong! Could not read DEADLINE.");
                }
                return new AddCommand(CommandType.DEADLINE, deadlineArgs[0], deadlineArgs[1]);
            case EVENT:
                String eventBody = sc.nextLine().trim();
                String[] eventArgs = eventBody.split("\\s/at\\s");
                if (eventArgs.length < 2 || eventArgs[0].isBlank() || eventArgs[1].isBlank()) {
                    throw new DukeException("Something went wrong! Could not read EVENT.");
                }
                return new AddCommand(CommandType.EVENT, eventArgs[0], eventArgs[1]);
            case LIST:
                return new ListCommand();
            case MARK:
                HashSet<Integer> indexesToMark = new HashSet<>();
                while (sc.hasNextInt()) {
                    indexesToMark.add(sc.nextInt() - 1);
                }
                return new MarkCommand(indexesToMark.toArray(new Integer[0]));
            case UNMARK:
                HashSet<Integer> indexesToUnmark = new HashSet<>();
                while (sc.hasNextInt()) {
                    indexesToUnmark.add(sc.nextInt() - 1);
                }
                return new UnmarkCommand(indexesToUnmark.toArray(new Integer[0]));
            case DELETE:
                HashSet<Integer> indexesToDelete = new HashSet<>();
                while (sc.hasNextInt()) {
                    indexesToDelete.add(sc.nextInt() - 1);
                }
                return new DeleteCommand(indexesToDelete.toArray(new Integer[0]));
            case SAVE:
                return new SaveCommand();
            case FIND:
                if (!sc.hasNext()) {
                    throw new DukeException("Something went wrong! Could not read FIND.");
                }
                return new FindCommand(sc.next());
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeException("I'm sorry, but I don't understand that.");
            }
        } catch (DukeException e) {
            return new WrongCommand(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
