package tako;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import tako.command.AddCommand;
import tako.command.Command;
import tako.command.DeleteCommand;
import tako.command.ExitCommand;
import tako.command.FindCommand;
import tako.command.ListCommand;
import tako.command.MarkCommand;

import tako.command.SortCommand;
import tako.task.Deadline;
import tako.task.Event;
import tako.task.Todo;

/**
 * Parses input from the user
 * such that they are valid commands.
 */
public class Parser {
    private enum Keyword {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE, FIND, SORT;

        //@@author Devashish Bansal
        // Reused from https://stackoverflow.com/questions/4936819/java-check-if-enum-contains-a-given-string
        // with minor modifications
        private static boolean hasKeyword(String s) {
            for (Keyword k : Keyword.values()) {
                if (k.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
        //@@author
    }

    /**
     * Returns the command parsed from the user's input.
     *
     * @param input User input from interacting with Tako.
     * @return Command.
     * @throws TakoException If the input cannot be parsed.
     */
    public static Command parse(String input) throws TakoException {
        String[] splitInput = input.trim().split(" ", 2);
        String keywordString = splitInput[0].toUpperCase();
        Keyword command;

        if (Keyword.hasKeyword(keywordString)) {
            command = Keyword.valueOf(keywordString);
        } else {
            throw new TakoException("The input is invalid.");
        }

        switch (command) {
        case BYE:
            return createExitCommand(splitInput.length);
        case LIST:
            return createListCommand(splitInput.length);
        case MARK:
            return createMarkCommand(splitInput);
        case TODO:
            return createTodoCommand(splitInput);
        case DEADLINE:
            return createDeadlineCommand(splitInput);
        case EVENT:
            return createEventCommand(splitInput);
        case DELETE:
            return createDeleteCommand(splitInput);
        case FIND:
            return createFindCommand(splitInput);
        case SORT:
            return createSortCommand(splitInput);
        default:
            assert false : "Unknown Command: " + command;
            throw new TakoException("The input is invalid.");
        }
    }

    private static ExitCommand createExitCommand(int length) throws TakoException {
        if (length == 1) {
            return new ExitCommand();
        } else {
            throw new TakoException("The input is invalid.");
        }
    }

    private static ListCommand createListCommand(int length) throws TakoException {
        if (length == 1) {
            return new ListCommand();
        } else {
            throw new TakoException("The input is invalid.");
        }
    }

    private static MarkCommand createMarkCommand(String[] splitInput) throws TakoException {
        try {
            if (splitInput.length == 1) {
                throw new TakoException("The task number to mark cannot be empty.");
            } else if (splitInput.length == 2) {
                String listNumberString = splitInput[1];
                int taskNumber = Integer.parseInt(listNumberString) - 1;
                return new MarkCommand(taskNumber);
            } else {
                throw new TakoException("The input is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new TakoException("The task number to mark is invalid.");
        }
    }

    private static AddCommand createTodoCommand(String[] splitInput) throws TakoException {
        if (splitInput.length == 1) {
            throw new TakoException("The description of this todo cannot be empty.");
        }
        String description = splitInput[1];
        assert !description.equals("") : "Empty description.";
        return new AddCommand(new Todo(description));
    }

    private static AddCommand createDeadlineCommand(String[] splitInput) throws TakoException {
        if (splitInput.length == 1) {
            throw new TakoException("The description of this deadline cannot be empty.");
        }
        String descriptionAndDateTime = splitInput[1];
        String[] splitDeadline = descriptionAndDateTime.split(" /by ", 2);
        if (splitDeadline.length == 2) {
            try {
                String description = splitDeadline[0];
                assert !description.equals("") : "Empty description.";
                LocalDateTime dateTime = LocalDateTime.parse(
                        splitDeadline[1].replace(' ', 'T'));
                return new AddCommand(new Deadline(description, dateTime));
            } catch (DateTimeParseException e) {
                throw new TakoException("Invalid date and time.\n"
                        + "Date and time should be of the format: yyyy-mm-dd hh:mm\n"
                        + "For example: 2019-10-15 10:30");
            }
        } else {
            throw new TakoException(
                    "The description of this deadline's date and time cannot be empty.");
        }
    }

    private static AddCommand createEventCommand(String[] splitInput) throws TakoException {
        if (splitInput.length == 1) {
            throw new TakoException("The description of this event cannot be empty.");
        }
        String descriptionAndDateTime = splitInput[1];
        String[] splitEvent = descriptionAndDateTime.split(" /at ", 2);
        if (splitEvent.length == 2) {
            try {
                String description = splitEvent[0];
                assert !description.equals("") : "Empty description.";
                LocalDateTime dateTime = LocalDateTime.parse(
                        splitEvent[1].replace(' ', 'T'));
                return new AddCommand(new Event(description, dateTime));
            } catch (DateTimeParseException e) {
                throw new TakoException("Invalid date and time.\n"
                        + "Date and time should be of the format: yyyy-mm-dd hh:mm\n"
                        + "For example: 2019-10-15 10:30");
            }
        } else {
            throw new TakoException(
                    "The description of this event's date and time cannot be empty.");
        }
    }

    private static DeleteCommand createDeleteCommand(String[] splitInput) throws TakoException {
        try {
            if (splitInput.length == 1) {
                throw new TakoException("The task number to delete cannot be empty.");
            } else if (splitInput.length == 2) {
                String listNumberString = splitInput[1];
                int taskNumber = Integer.parseInt(listNumberString) - 1;
                return new DeleteCommand(taskNumber);
            } else {
                throw new TakoException("The input is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new TakoException("The task number to delete is invalid.");
        }
    }

    private static FindCommand createFindCommand(String[] splitInput) throws TakoException {
        if (splitInput.length == 1) {
            throw new TakoException("The task to find cannot be empty.");
        } else if (splitInput.length == 2) {
            String wordToFind = splitInput[1];
            return new FindCommand(wordToFind);
        } else {
            throw new TakoException("The input is invalid.");
        }
    }

    // sort date desc
    private static SortCommand createSortCommand(String[] splitInput) throws TakoException {
        if (splitInput.length == 1) {
            throw new TakoException("The way to sort by cannot be empty.");
        }
        String sortArgs = splitInput[1];
        String[] splitSortArgs = sortArgs.split(" ");
        boolean isAscending = isAscending(splitSortArgs);
        String sortByString = splitSortArgs[0].toUpperCase();

        if (Sort.hasSortBy(sortByString)) {
            Sort sortBy = Sort.valueOf(sortByString);
            return new SortCommand(sortBy, isAscending);
        } else {
            throw new TakoException("The input is invalid.");
        }
    }

    private static boolean isAscending(String[] splitSortArgs) throws TakoException {
        if (splitSortArgs.length == 1) {
            return true;
        } else if (splitSortArgs.length == 2 && splitSortArgs[1].equals("desc")) {
            return false;
        } else {
            throw new TakoException("The input is invalid.");
        }
    }
}
