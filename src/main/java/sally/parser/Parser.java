package sally.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sally.command.AddDeadlineCommand;
import sally.command.AddEventCommand;
import sally.command.AddTodoCommand;
import sally.command.ByeCommand;
import sally.command.Command;
import sally.command.DeleteCommand;
import sally.command.FindCommand;
import sally.command.HelpCommand;
import sally.command.ListCommand;
import sally.command.MarkCommand;
import sally.command.UnmarkCommand;
import sally.exception.SallyException;

/**
 * Parser class to parse inputs into commands.
 *
 * @author liviamil
 */

public class Parser {

    /**
     * Returns command according to the parsed string.
     *
     * @param command user input
     * @return Command to be executed
     * @throws SallyException when illegal input is encountered
     */
    public static Command parseCommand(String command) throws SallyException {
        assert !command.isEmpty();

        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.startsWith("list")) {
            return new ListCommand();
        } else if (command.startsWith("delete")) {
            return parseDelete(command);
        } else if (command.startsWith("unmark")) {
            return parseUnmark(command);
        } else if (command.startsWith("mark")) {
            return parseMark(command);
        } else if (command.startsWith("find")) {
            return parseFind(command);
        } else if (command.startsWith("help")) {
            return parseHelp(command);
        } else if (command.startsWith("todo")) {
            return parseToDo(command);
        } else if (command.startsWith("event")) {
            return parseEvent(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadline(command);
        } else {
            throw new SallyException("Sorry, I am still learning and don't understand that :<");
        }
    }

    /**
     * Parses the given command and invokes DeleteCommand when conditions are fulfilled.
     *
     * @param command input
     * @return DeleteCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseDelete(String command) throws SallyException {
        if (command.length() > 7 && command.substring(7).trim().chars().allMatch(Character::isDigit)) {
            int taskNum = Integer.parseInt(command.substring(7).trim()) - 1;
            checkDeleteValidity(taskNum);
            return new DeleteCommand(taskNum);
        } else {
            throw new SallyException("Oops! I don't understand that, make sure to enter 'delete <index>' :)");
        }
    }

    /**
     * Parses the given command and invokes UnmarkCommand when conditions are fulfilled.
     *
     * @param command input
     * @return UnmarkCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseUnmark(String command) throws SallyException {
        if (command.equals("unmark")) {
            throw new SallyException("Oops! Make sure to enter 'unmark <index>'");
        }
        String taskNumString = command.replace("unmark ", "");
        if (taskNumString.equals("")) {
            throw new SallyException("Oops! Make sure to enter 'unmark <index>'");
        }
        int taskNum = Integer.parseInt(taskNumString) - 1; // -1 so that index is constant
        assert taskNum >= 0;
        checkUnmarkValidity(taskNum);
        return new UnmarkCommand(taskNum);
    }

    /**
     * Parses the given command and invokes MarkCommand when conditions are fulfilled.
     *
     * @param command input
     * @return MarkCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseMark(String command) throws SallyException {
        if (command.equals("mark")) {
            throw new SallyException("Oops! Make sure to enter 'mark <index>'");
        }
        String taskNumString = command.replace("mark ", "");
        if (taskNumString.equals("")) {
            throw new SallyException("Oops! Make sure to enter 'mark <index>'");
        }
        int taskNum = Integer.parseInt(taskNumString) - 1; // -1 so that index is constant
        checkMarkValidity(taskNum);
        return new MarkCommand(taskNum);
    }

    /**
     * Parses the given command and invokes FindCommand when conditions are fulfilled.
     *
     * @param command input
     * @return FindCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseFind(String command) throws SallyException {
        String keyword = command.replace("find ", "");
        if (keyword.isEmpty()) {
            throw new SallyException("Oops! I don't understand that, make sure to enter 'find <keyword>' :)");
        }
        return new FindCommand(keyword);
    }

    /**
     * Parses the given command and invokes HelpCommand when conditions are fulfilled.
     *
     * @param command input
     * @return HelpCommand
     * @throws SallyException when invalid input is parsed in
     */
    public static Command parseHelp(String command) throws SallyException {
        if (command.length() <= 4) {
            return new HelpCommand();
        }
        String keyword = command.replace("help ", "");
        return new HelpCommand(keyword);
    }

    /**
     * Parses the given command and invokes AddTodoCommand when conditions are fulfilled.
     *
     * @param command input
     * @return AddTodoCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseToDo(String command) throws SallyException {
        String todo = command.replace("todo ", "");
        checkTodoValidity(todo);
        assert !todo.isEmpty();
        return new AddTodoCommand(todo);
    }

    /**
     * Parses the given command and invokes AddEventCommand when conditions are fulfilled.
     *
     * @param command input
     * @return AddEventCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseEvent(String command) throws SallyException {
        if (command.length() <= 5 || !command.contains("/at ")) {
            throw new SallyException("Oops! "
                    + "I don't understand that, make sure to enter 'event <description> /at <venue>' :)");
        }
        String description = command.substring(5, command.indexOf("/at") - 1);
        System.out.println("description: " + description + description.isEmpty());
        if (!description.isEmpty()) {
            String at = command.substring(command.indexOf("/at") + 4);
            return new AddEventCommand(description, at);
        } else {
            throw new SallyException("Oops! "
                    + "I don't understand that, make sure to enter 'event <description> /at <venue>' :)");
        }
    }

    /**
     * Parses the given command and invokes AddDeadlineCommand when conditions are fulfilled.
     *
     * @param command input
     * @return AddDeadlineCommand
     * @throws SallyException when invalid or incomplete input is parsed in
     */
    public static Command parseDeadline(String command) throws SallyException {
        if (!command.contains("/by ")) {
            throw new SallyException("Oops! I don't understand that, make sure to enter "
                    + "'deadline <description> /by <d-MM-yyyy>' or 'deadline <description> /by <time>' :)");
        }
        String description = command.substring(8, command.indexOf("/by") - 1);
        if (description.isEmpty()) {
            throw new SallyException("Oops! I don't understand that, make sure to enter "
                    + "'deadline <description> /by <d-MM-yyyy>' or 'deadline <description> /by <time>' :)");
        }
        String by = command.substring(command.indexOf("/by") + 4);
        if (by.isEmpty()) {
            throw new SallyException("Oops! I don't understand that, make sure to enter "
                    + "'deadline <description> /by <d-MM-yyyy>' or 'deadline <description> /by <time>' :)");

        }
        if (isDate(by)) {
            LocalDate byDate;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
            try {
                byDate = LocalDate.parse(by, formatter);
            } catch (DateTimeParseException e) {
                throw new SallyException(e.getMessage());
            }
            assert isDate(by);
            return new AddDeadlineCommand(description, byDate);
        } else {
            return new AddDeadlineCommand(description, by);
        }
    }

    /**
     * Validates whether task is available to unmark
     *
     * @param taskNum task index to be unmarked
     * @throws SallyException when invalid input is encountered
     */
    public static void checkUnmarkValidity(int taskNum) throws SallyException {
        if (taskNum < 0) {
            throw new SallyException("Oops! Enter a valid task number!");
        }
    }

    /**
     * Validates whether task is available to mark
     *
     * @param taskNum task index to be marked
     * @throws SallyException when invalid input is encountered
     */
    public static void checkMarkValidity(int taskNum) throws SallyException {
        if (taskNum < 0) {
            throw new SallyException("Oops! Enter a valid task number!");
        }
    }

    /**
     * Validates whether task is available to delete
     *
     * @param taskNum task index to be unmarked
     * @throws SallyException when invalid input is encountered
     */
    public static void checkDeleteValidity(int taskNum) throws SallyException {
        if (taskNum < 0) {
            throw new SallyException("Oops! Enter a valid task number!");
        }
    }

    /**
     * Validates whether task is a valid todo command
     *
     * @param todo command string of todo description
     * @throws SallyException when invalid input is encountered
     */
    public static void checkTodoValidity(String todo) throws SallyException {
        if (todo.isEmpty() || todo.equals("todo")) {
            throw new SallyException("Oops! I don't understand that, make sure to enter 'todo <description>' :)");
        }
    }

    /**
     * Returns true if deadline entered is date, returns false otherwise.
     *
     * @param by deadline parsed in
     * @return true if deadline parsed is valid date.
     */
    protected static boolean isDate(String by) {
        // Expected input date format: dd-mmm-yyyy
        String[] splitBy = by.split("-");
        if (splitBy.length != 3) {
            return false;
        }
        return true;
    }
}
