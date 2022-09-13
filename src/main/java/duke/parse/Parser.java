package duke.parse;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidPriorityException;
import duke.exception.MissingDeadlineDescriptionException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingEventDescriptionException;
import duke.exception.MissingTargetException;

/**
 * Part of the chatbot that deals with user commands.
 */
public class Parser {

    /**
     * Returns the appropriate command after parsing the user input if its valid.
     *
     * @param input The user input.
     * @return The command that the user input.
     * @throws InvalidInputException When the user inputs an invalid command.
     * @throws MissingDescriptionException When the user did not input the description after the command.
     */
    public Command parse(String input) throws InvalidInputException, MissingDescriptionException {
        Command commandToReturn = null;
        String[] inputSpilt = input.split(" ", 2);
        if (inputSpilt.length == 2) {
            String firstChar = inputSpilt[0];
            if (firstChar.equals(MarkCommand.COMMAND_WORD)) {
                commandToReturn = new MarkCommand(handleParseInt(inputSpilt[1], "mark as done"));
            } else if (firstChar.compareTo(UnmarkCommand.COMMAND_WORD) == 0) {
                commandToReturn = new UnmarkCommand(handleParseInt(inputSpilt[1], "mark as not done"));
            } else if (firstChar.compareTo(DeleteCommand.COMMAND_WORD) == 0) {
                commandToReturn = new DeleteCommand(handleParseInt(inputSpilt[1], "delete"));
            } else if (firstChar.compareTo(ToDoCommand.COMMAND_WORD) == 0) {
                commandToReturn = new ToDoCommand(inputSpilt[1]);
            } else if (firstChar.compareTo(DeadlineCommand.COMMAND_WORD) == 0) {
                commandToReturn = handleDeadlineParse(inputSpilt[1]);
            } else if (firstChar.compareTo(EventCommand.COMMAND_WORD) == 0) {
                commandToReturn = handleEventParse(inputSpilt[1]);
            } else if (firstChar.compareTo(FindCommand.COMMAND_WORD) == 0) {
                commandToReturn = new FindCommand(inputSpilt[1]);
            } else if (firstChar.compareTo(PriorityCommand.COMMAND_WORD) == 0) {
                commandToReturn = handlePriorityParse(inputSpilt[1]);
            } else {
                throw new InvalidInputException();
            }
        } else if (inputSpilt.length == 1) {
            String firstChar = inputSpilt[0];
            if (firstChar.compareTo(ByeCommand.COMMAND_WORD) == 0) {
                commandToReturn = new ByeCommand();
            } else if (firstChar.compareTo(ListCommand.COMMAND_WORD) == 0) {
                commandToReturn = new ListCommand();
            } else if (firstChar.compareTo(ToDoCommand.COMMAND_WORD) == 0
                    || firstChar.compareTo(DeadlineCommand.COMMAND_WORD) == 0
                    || firstChar.compareTo(EventCommand.COMMAND_WORD) == 0) {
                throw new MissingDescriptionException(inputSpilt[0]);
            } else {
                throw new InvalidInputException();
            }
        } else {
            throw new InvalidInputException();
        }
        return commandToReturn;
    }

    private int handleParseInt(String input, String command) throws MissingTargetException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new MissingTargetException(command);
        }
    }

    private Command handlePriorityParse(String input) throws InvalidPriorityException {
        String[] prioritySpilt = input.split(" ", 2);
        int taskToChangePriority = handleParseInt(prioritySpilt[0], "change priority level");
        if (prioritySpilt.length != 2) {
            throw new InvalidPriorityException();
        }

        if (prioritySpilt[1].equals("high")) {
            return new PriorityCommand(taskToChangePriority, prioritySpilt[1]);
        } else if (prioritySpilt[1].equals("medium")) {
            return new PriorityCommand(taskToChangePriority, prioritySpilt[1]);
        } else if (prioritySpilt[1].equals("low")) {
            return new PriorityCommand(taskToChangePriority, prioritySpilt[1]);
        } else {
            throw new InvalidPriorityException();
        }

    }

    private Command handleDeadlineParse(String input) throws MissingDeadlineDescriptionException {
        String[] deadlineSpilt = input.split("/by ", 2);
        try {
            if (deadlineSpilt.length != 2) {
                throw new MissingDeadlineDescriptionException();
            } else {
                String[] deadlineDateTimeSpilt = deadlineSpilt[1].split(" ", 2);
                LocalDate localDate = LocalDate.parse(deadlineDateTimeSpilt[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (deadlineDateTimeSpilt.length == 1) {
                    return new DeadlineCommand(deadlineSpilt[0], localDate, null);
                } else {
                    LocalTime localTime = LocalTime.parse(deadlineDateTimeSpilt[1],
                            DateTimeFormatter.ofPattern("HHmm"));
                    return new DeadlineCommand(deadlineSpilt[0], localDate, localTime);
                }
            }
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException("OOPS! The date and time format for deadline is incorrect\n"
                    + "FORMAT: /by <yyyy-MM-dd HHmm / yyyy-MM-dd>");
        }

    }

    private Command handleEventParse(String input) throws MissingEventDescriptionException {
        String[] eventSpilt = input.split("/at ", 2);
        if (eventSpilt.length != 2) {
            throw new MissingEventDescriptionException();
        } else {
            return new EventCommand(eventSpilt[0], eventSpilt[1]);
        }
    }

}
