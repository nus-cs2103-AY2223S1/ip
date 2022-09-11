package spongebob;

import java.util.HashSet;
import java.util.Scanner;

import spongebob.command.AddCommand;
import spongebob.command.CommandType;
import spongebob.command.DeleteCommand;
import spongebob.command.ExitCommand;
import spongebob.command.FindCommand;
import spongebob.command.ICommand;
import spongebob.command.ListCommand;
import spongebob.command.MarkCommand;
import spongebob.command.SaveCommand;
import spongebob.command.UnmarkCommand;
import spongebob.command.WrongCommand;

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
                throw new SpongebobException("I'm sorry, but I don't understand that.");
            }
            CommandType type = CommandType.COMMAND_MAP.get(command);
            switch (type) {
            case TODO:
                String todoBody = sc.nextLine().trim();
                if (todoBody.isBlank()) {
                    throw new SpongebobException("Something went wrong! Could not read TODO.");
                }
                return new AddCommand(CommandType.TODO, todoBody);
            case DEADLINE:
                String deadlineBody = sc.nextLine().trim();
                String[] deadlineArgs = deadlineBody.split("\\s/by\\s");
                if (deadlineArgs.length < 2 || deadlineArgs[0].isBlank() || deadlineArgs[1].isBlank()) {
                    throw new SpongebobException("Something went wrong! Could not read DEADLINE.");
                }
                return new AddCommand(CommandType.DEADLINE, deadlineArgs[0], deadlineArgs[1]);
            case EVENT:
                String eventBody = sc.nextLine().trim();
                String[] eventArgs = eventBody.split("\\s/at\\s");
                if (eventArgs.length < 2 || eventArgs[0].isBlank() || eventArgs[1].isBlank()) {
                    throw new SpongebobException("Something went wrong! Could not read EVENT.");
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
                    throw new SpongebobException("Something went wrong! Could not read FIND.");
                }
                return new FindCommand(sc.next());
            case BYE:
                return new ExitCommand();
            default:
                throw new SpongebobException("I'm sorry, but I don't understand that.");
            }
        } catch (SpongebobException e) {
            return new WrongCommand(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
