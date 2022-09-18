package unc;

import unc.command.Command;
import unc.command.DeadlineCommand;
import unc.command.DeleteCommand;
import unc.command.EventCommand;
import unc.command.ExitCommand;
import unc.command.FindCommand;
import unc.command.ListCommand;
import unc.command.MarkCommand;
import unc.command.SortCommand;
import unc.command.TodoCommand;
import unc.command.UnmarkCommand;

/**
 * Class to create commands from input strings.
 */
public class Parser {
    private enum ValidInput {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        FIND,
        SORT

    }

    /**
     * Reads the user input to translate to valid commands.
     *
     * @param input Entire line of user input.
     * @return Adequate command based on keyword.
     */
    public static Command parse(String input) throws UncException {
        if (input.isBlank()) {
            throw new UncException("Sorry, I didn't catch that.");
        }
        String[] words = input.split("\\s+", 2);
        try {
            ValidInput verb = ValidInput.valueOf(words[0].toUpperCase());
            switch (verb) {
            case LIST:
                return new ListCommand();
            case MARK:
                try {
                    int index = Integer.parseInt(words[1]);
                    return new MarkCommand(index);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("Please give me the index of task to be marked.");
                } catch (NumberFormatException numberFormatException) {
                    throw new UncException("Index of task should be an integer");
                }
            case UNMARK:
                try {
                    int index = Integer.parseInt(words[1]);
                    return new UnmarkCommand(index);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("Please give me the index of task to be unmarked.");
                } catch (NumberFormatException numberFormatException) {
                    throw new UncException("Index of task should be an integer");
                }
            case DELETE:
                try {
                    int index = Integer.parseInt(words[1]);
                    return new DeleteCommand(index);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("Please give me the index of task to be deleted.");
                } catch (NumberFormatException numberFormatException) {
                    throw new UncException("Index of task should be an integer");
                }
            case TODO:
                try {
                    String description = words[1];
                    return new TodoCommand(description);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("What's the task?");
                }
            case DEADLINE:
                try {
                    String description = words[1];
                    return new DeadlineCommand(description);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("What's the task?");
                }
            case EVENT:
                try {
                    String description = words[1];
                    return new EventCommand(description);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("What's the task?");
                }
            case BYE:
                return new ExitCommand();
            case FIND:
                try {
                    String keyword = words[1];
                    return new FindCommand(keyword);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new UncException("What's the word to find?");
                }
            case SORT:
                return new SortCommand();
            default:
                throw new UncException("I don't know what that means.");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new UncException("I don't know what that means.");
        }
    }
}
