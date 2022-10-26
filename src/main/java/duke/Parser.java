package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.TaskType;

/**
 * Utility class to convert user input into a Command.
 */
public class Parser {
    /**
     * Parses raw input from user into a specific Command.
     *
     * @param fullCommand Raw input from user.
     * @return Command that corresponds to input.
     * @throws DukeException If fullCommand is of an invalid format.
     */
    public Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.strip();
        String[] fullCommandArr = fullCommand.split(" ", 2);
        String commandName = fullCommandArr[0].toUpperCase();
        String restOfCommand = fullCommandArr.length > 1 ? fullCommandArr[1].strip() : "";
        switch (commandName) {
        case "TODO":
            return handleAddTodo(restOfCommand);
        case "DEADLINE":
            return handleAddDeadline(restOfCommand);
        case "EVENT":
            return handleAddEvent(restOfCommand);
        case "FIND":
            return handleFind(restOfCommand);
        case "DELETE":
            return handleDelete(restOfCommand);
        case "MARK":
            return handleMark(restOfCommand);
        case "UNMARK":
            return handleUnmark(restOfCommand);
        case "LIST":
            return new ListCommand();
        case "BYE":
            return new ExitCommand();
        default:
            throw new DukeException("Invalid command!");
        }
    }

    AddCommand handleAddTodo(String restOfCommand) throws DukeException {
        String description = restOfCommand;
        if (description.isBlank()) {
            throw new DukeException("Description cannot be empty.");
        }
        return new AddCommand(TaskType.TODO, description, null);
    }

    AddCommand handleAddDeadline(String restOfCommand) throws DukeException {
        try {
            int index = restOfCommand.lastIndexOf("/by");
            if (index == -1) {
                throw new DukeException("To add a deadline, please specify '/by dd/mm/yy'.");
            }

            String description = restOfCommand.substring(0, index);
            if (description.isBlank()) {
                throw new DukeException("Description cannot be empty.");
            }

            String deadlineString = restOfCommand.substring(index + 3).strip();
            LocalDate deadline =
                    LocalDate.parse(deadlineString, DateTimeFormatter.ofPattern("dd/MM/yy"));
            return new AddCommand(TaskType.DEADLINE, description, deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("To add a deadline, please specify '/by dd/mm/yy'.");
        }
    }
    
    AddCommand handleAddEvent(String restOfCommand) throws DukeException {
        try {
            int index = restOfCommand.lastIndexOf("/at");
            if (index == -1) {
                throw new DukeException("To add an event, please specify '/at dd/mm/yy'.");
            }

            String description = restOfCommand.substring(0, index);
            if (description.isBlank()) {
                throw new DukeException("Description cannot be empty.");
            }

            String eventDateString = restOfCommand.substring(index + 3).strip();
            LocalDate eventDate =
                    LocalDate.parse(eventDateString, DateTimeFormatter.ofPattern("dd/MM/yy"));
            return new AddCommand(TaskType.EVENT, description, eventDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("To add an event, please specify '/at dd/mm/yy'.");
        }
    }

    FindCommand handleFind(String restOfCommand) throws DukeException {
        String keyword = restOfCommand;
        if (keyword.isBlank()) {
            throw new DukeException("Keyword cannot be empty.");
        }
        return new FindCommand(keyword);
    }

    DeleteCommand handleDelete(String restOfCommand) throws DukeException {
        try {
            // Delete now supports batch operations
            String[] indexStrings = restOfCommand.split(",");
            List<Integer> indices = new ArrayList<>();

            for (String i : indexStrings) {
                indices.add(Integer.valueOf(i.strip()) - 1);
            }
            return new DeleteCommand(indices);
        } catch (NumberFormatException e) {
            throw new DukeException("Task ID supplied is not a number.");
        }
    }

    MarkCommand handleMark(String restOfCommand) throws DukeException {
        try {
            // Mark now supports batch operations
            String[] indexStrings = restOfCommand.split(",");
            List<Integer> indices = new ArrayList<>();

            for (String i : indexStrings) {
                indices.add(Integer.valueOf(i.strip()) - 1);
            }
            return new MarkCommand(indices);
        } catch (NumberFormatException e) {
            throw new DukeException("Task ID supplied is not a number.");
        }
    }
    
    UnmarkCommand handleUnmark(String restOfCommand) throws DukeException {
        try {
            // Unmark now supports batch operations
            String[] indexStrings = restOfCommand.split(",");
            List<Integer> indices = new ArrayList<>();

            for (String i : indexStrings) {
                indices.add(Integer.valueOf(i.strip()) - 1);
            }
            return new UnmarkCommand(indices);
        } catch (NumberFormatException e) {
            throw new DukeException("Task ID supplied is not a number.");
        }
    }
}
