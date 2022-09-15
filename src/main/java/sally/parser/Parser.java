package sally.parser;

import sally.command.AddDeadlineCommand;
import sally.command.AddEventCommand;
import sally.command.AddTodoCommand;
import sally.command.ByeCommand;
import sally.command.Command;
import sally.command.DeleteCommand;
import sally.command.FindCommand;
import sally.command.ListCommand;
import sally.command.MarkCommand;
import sally.command.UnmarkCommand;
import sally.exception.SallyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        // Bye
        if (command.equals("bye")) {
            return new ByeCommand();
        }

        // List
        else if (command.startsWith("list")) {
            return new ListCommand();
        }

        // Delete
        else if (command.startsWith("delete")) {
            if (command.length() > 7 && command.substring(7).trim().chars().allMatch(Character::isDigit)) {
                int taskNum = Integer.parseInt(command.substring(7).trim()) - 1;
                assert taskNum >= 0;
                return new DeleteCommand(taskNum);
            } else {
                throw new SallyException.SallyInvalidInputException();
            }
        }

        // Unmark
        else if (command.startsWith("unmark")) {
            int taskNum = Integer.parseInt(command.substring(7)) - 1; // -1 so that index is constant
            assert taskNum >= 0;
            checkUnmarkValidity(taskNum);
            return new UnmarkCommand(taskNum);
        }

        // Mark
        else if (command.startsWith("mark")) {
            int taskNum = Integer.parseInt(command.substring(5)) - 1; // -1 so that index is constant
            assert taskNum >= 0;
            checkMarkValidity(taskNum);
            return new MarkCommand(taskNum);
        }

        // Find
        else if (command.startsWith("find")) {
            String keyword = command.replace("find ", "");
            if (keyword.isEmpty()) {
                throw new SallyException.SallyInvalidInputException();
            }
            return new FindCommand(keyword);
        }

        // sally.task.Task Commands
        else {
            // ToDos
            if (command.startsWith("todo")) {
                String todo = command.replace("todo ", "");
                checkTodoValidity(todo);
                assert !todo.isEmpty();
                return new AddTodoCommand(todo);
            }

            // Deadlines
            else if (command.startsWith("deadline")) {
                String description = command.substring(9, command.indexOf("/by") - 1);
                String by = command.substring(command.indexOf("/by") + 4);
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

            // Events
            else if (command.startsWith("event")) {
                String description, at;
                if (command.length() <= 5) {
                    throw new SallyException.SallyInvalidInputException();
                } else if (command.contains("/at ")) {
                    description = command.substring(6, command.indexOf("/at") - 1);
                    at = command.substring(command.indexOf("/at") + 4);
                    return new AddEventCommand(description, at);
                } else {
                    throw new SallyException.SallyNoPlaceException();
                }
            }

            // Any other messages
            else {
                throw new SallyException.SallyInvalidInputException();
            }
        }
    }

    /**
     * Validates whether task is available to unmark
     *
     * @param taskNum task index to be unmarked
     * @throws SallyException when invalid input is encountered
     */
    public static void checkUnmarkValidity(int taskNum) throws SallyException {
        if (taskNum < 1) {
            throw new SallyException.SallyTaskNotFoundException();
        }
    }

    /**
     * Validates whether task is available to mark
     *
     * @param taskNum task index to be marked
     * @throws SallyException when invalid input is encountered
     */
    public static void checkMarkValidity(int taskNum) throws SallyException {
        if (taskNum < 1) {
            throw new SallyException.SallyTaskNotFoundException();
        }
    }

    /**
     * Validates whether task is a valid todo command
     *
     * @param todo command string of todo description
     * @throws SallyException when invalid input is encountered
     */
    public static void checkTodoValidity(String todo) throws SallyException {
        if (todo.isEmpty()) {
            throw new SallyException.SallyNoDescriptionException();
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
