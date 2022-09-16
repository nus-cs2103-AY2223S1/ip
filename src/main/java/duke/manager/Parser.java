package duke.manager;

import duke.exception.DukeException;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles the parsing of the user's input into the appropriate command to be executed.
 */
public class Parser {
    /**
     * Returns the appropriate command from parsing the user's input.
     *
     * @param input The user's given input.
     * @return The appropriate command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    public static Command parseInput(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("mark") || input.startsWith("unmark")) {
            return getMarkCommand(input);
        }

        if (input.startsWith("delete")) {
            return getDeleteCommand(input);
        }

        if (input.startsWith("todo")) {
            return getTodoCommand(input);
        }

        if (input.startsWith("deadline")) {
            return getDeadlineCommand(input);
        }

        if (input.startsWith("event")) {
            return getEventCommand(input);
        }

        if (input.startsWith("find")) {
            return getFindCommand(input);
        }

        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Validates the user's input and returns the mark or unmark command.
     *
     * @param input The user's given input.
     * @return The appropriate mark or unmark command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    private static Command getMarkCommand(String input) throws DukeException {
        String[] parts = input.split(" ");

        // Input validation
        if (parts.length != 2) {
            throw new DukeException("Wrong input format! mark/unmark <item number>\ne.g. 'mark 3'");
        }

        String mark = parts[0];
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid duke.task number! mark/unmark <item number>\ne.g. 'mark 3'");
        }

        return new MarkCommand(mark, taskIndex);
    }

    /**
     * Validates the user's input and returns the delete command.
     *
     * @param input The user's given input.
     * @return The delete command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    private static Command getDeleteCommand(String input) throws DukeException {
        String[] parts = input.split(" ");

        // Input validation
        if (parts.length != 2) {
            throw new DukeException("Wrong format! delete <item number>\ne.g. 'delete 3'");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid duke.task number! delete <item number>\ne.g. 'delete 3'");
        }

        return new DeleteCommand(taskIndex);
    }

    /**
     * Validates the user's input and returns the appropriate to do command.
     *
     * @param input The user's given input.
     * @return The to do command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    private static Command getTodoCommand(String input) throws DukeException {
        String[] parts = input.split("todo", 2);
        String description = parts[1].trim();

        if (parts[1].equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task newTask = new Todo(description);

        return new AddCommand(newTask);
    }

    /**
     * Validates the user's input and returns the appropriate event command.
     *
     * @param input The user's given input.
     * @return The event command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    private static Command getEventCommand(String input) throws DukeException {
        String[] parts = input.split("event", 2);

        if (parts[1].equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        String[] details = parts[1].split("/at", 2);

        String description = details[0].trim();;
        LocalDate date;

        try {
            String inputDate = details[1].trim();
            if (inputDate.equals("")) {
                throw new DukeException("An event needs a date! e.g. event meeting /at 2023-04-05");
            }
            date = LocalDate.parse(inputDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("An event needs a date! e.g. event meeting /at 2023-04-05");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be in proper format!");
        }

        Task newTask = new Event(description, date);

        return new AddCommand(newTask);
    }

    /**
     * Validates the user's input and returns the appropriate deadline command.
     *
     * @param input The user's given input.
     * @return The deadline command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    private static Command getDeadlineCommand(String input) throws DukeException {
        String[] parts = input.split("deadline", 2);

        if (parts[1].equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] details = parts[1].split("/by", 2);
        String description = details[0].trim();;
        LocalDate date;
        try {
            String inputDate = details[1].trim();
            if (inputDate.equals("")) {
                throw new DukeException("A deadline needs a by date! e.g. deadline complete homework /by 2022-04-05");
            }
            date = LocalDate.parse(inputDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("A deadline needs a by date! e.g. deadline complete homework /by 2022-04-05");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be in proper format!");
        }
        Task newTask = new Deadline(description, date);
        return new AddCommand(newTask);
    }

    /**
     * Validates the user's input and returns the appropriate find command.
     *
     * @param input The user's given input.
     * @return The find command to be executed.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    private static Command getFindCommand(String input) throws DukeException {
        String[] parts = input.split(" ");

        String keyword = "";
        try {
            keyword = parts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("A find command needs a keyword!");
        }
        return new FindCommand(keyword);
    }

}
