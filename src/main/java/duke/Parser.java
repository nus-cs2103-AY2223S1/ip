package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.strip();
        String[] fullCommandArr = fullCommand.split(" ", 2);
        String commandName = fullCommandArr[0].toUpperCase();
        String restOfCommand = fullCommandArr.length > 1 ? fullCommandArr[1].strip() : "";
        String description;
        switch (commandName) {
        case "TODO":
            description = restOfCommand;
            if (description.isBlank()) {
                throw new DukeException("Description cannot be empty.");
            }
            return new AddCommand(TaskType.TODO, description, null);
        case "DEADLINE":
            try {
                int index = restOfCommand.lastIndexOf("/by");
                if (index == -1) {
                    throw new DukeException("To add a deadline, please specify '/by dd/mm/yy'.");
                }

                description = restOfCommand.substring(0, index);
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
        case "EVENT":
            try {
                int index = restOfCommand.lastIndexOf("/at");
                if (index == -1) {
                    throw new DukeException("To add an event, please specify '/at dd/mm/yy'.");
                }

                description = restOfCommand.substring(0, index);
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
        case "FIND":
            String keyword = restOfCommand;
            if (keyword.isBlank()) {
                throw new DukeException("Keyword cannot be empty.");
            }
            return new FindCommand(keyword);
        case "DELETE":
            try {
                int index = Integer.valueOf(restOfCommand) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Task ID supplied is not a number.");
            }
        case "MARK":
            try {
                int index = Integer.valueOf(restOfCommand) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Task ID supplied is not a number.");
            }
        case "UNMARK":
            try {
                int index = Integer.valueOf(restOfCommand) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Task ID supplied is not a number.");
            }
        case "LIST":
            return new ListCommand();
        case "BYE":
            return new ExitCommand();
        default:
            throw new DukeException("Invalid command!");
        }
    }
}
