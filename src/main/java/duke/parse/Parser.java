package duke.parse;

import duke.command.*;
import duke.exception.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Command parse(String input) throws InvalidInputException, MissingDescriptionException {
        Command commandToReturn = null;
        String[] inputSpilt = input.split(" ", 2);
        if (inputSpilt.length == 2) {
            if (inputSpilt[0].equals(MarkCommand.COMMAND_WORD)) {
                commandToReturn = new MarkCommand(HandleParseInt(inputSpilt[1], "mark as done"));
            } else if (inputSpilt[0].compareTo(UnmarkCommand.COMMAND_WORD) == 0) {
                commandToReturn = new UnmarkCommand(HandleParseInt(inputSpilt[1], "mark as not done"));
            } else if (inputSpilt[0].compareTo(DeleteCommand.COMMAND_WORD) == 0) {
                commandToReturn = new DeleteCommand(HandleParseInt(inputSpilt[1], "delete"));
            } else if (inputSpilt[0].compareTo(ToDoCommand.COMMAND_WORD) == 0) {
                commandToReturn = new ToDoCommand(inputSpilt[1]);
            } else if (inputSpilt[0].compareTo(DeadlineCommand.COMMAND_WORD) == 0) {
                commandToReturn = HandleDeadlineParse(inputSpilt[1]);
            } else if (inputSpilt[0].compareTo(EventCommand.COMMAND_WORD) == 0) {
                commandToReturn = HandleEventParse(inputSpilt[1]);
            } else {
                throw new InvalidInputException();
            }
        } else if (inputSpilt.length == 1) {
            if (inputSpilt[0].compareTo(ByeCommand.COMMAND_WORD) == 0) {
                commandToReturn = new ByeCommand();
            } else if (inputSpilt[0].compareTo(ListCommand.COMMAND_WORD) == 0) {
                commandToReturn = new ListCommand();
            } else if (inputSpilt[0].compareTo(ToDoCommand.COMMAND_WORD) == 0 ||
                    inputSpilt[0].compareTo(DeadlineCommand.COMMAND_WORD) == 0 ||
                    inputSpilt[0].compareTo(EventCommand.COMMAND_WORD) == 0) {
                throw new MissingDescriptionException(inputSpilt[0]);
            } else {
                throw new InvalidInputException();
            }
        } else {
            throw new InvalidInputException();
        }
        return commandToReturn;
    }

    private int HandleParseInt(String input, String command) throws MissingTargetException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new MissingTargetException(command);
        }
    }

    private Command HandleDeadlineParse(String input) throws MissingDeadlineDescriptionException {
        String[] deadlineSpilt = input.split("/by ", 2);
        Command commandToReturn;
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
            throw new InvalidDateTimeException("OOPS! The date and time format for deadline is incorrect\n" +
                    "FORMAT: /by <yyyy-MM-dd HHmm / yyyy-MM-dd>");
        }

    }

    private Command HandleEventParse(String input) throws MissingEventDescriptionException {
        String[] eventSpilt = input.split("/at ", 2);
        if (eventSpilt.length != 2) {
            throw new MissingEventDescriptionException();
        } else {
            return new EventCommand(eventSpilt[0], eventSpilt[1]);
        }
    }

}
