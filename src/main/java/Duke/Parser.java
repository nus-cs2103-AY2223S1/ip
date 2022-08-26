package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Duke.Command.AddCommand;
import Duke.Command.Command;
import Duke.Command.DelCommand;
import Duke.Command.ExitCommand;
import Duke.Command.InvalidCommandException;
import Duke.Command.ListCommand;
import Duke.Command.MarkCommand;
import Duke.Command.UnmarkCommand;
import Duke.Task.DeadlineTask;
import Duke.Task.EventTask;
import Duke.Task.ToDoTask;

public class Parser {

    public static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

    private static final String INVALID_TIME_FORMAT_MSG = 
            "☹ OOPS!!! Please use the following format for time dd.mm.yyyy hh:mm";

    private static final String INVALID_NUMBER_FORMAT_MSG = 
            "☹ OOPS!!! Please use the following format for time dd.mm.yyyy hh:mm";

    public static Command parse(String input) throws InvalidCommandException {
        String errMsg = "";
        if (input.startsWith("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            try {
                int taskID = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                return new MarkCommand(taskID);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_FORMAT_MSG);
            }
        } else if (input.startsWith("unmark")) {
            try {
                int taskID = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                return new UnmarkCommand(taskID);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_FORMAT_MSG);
            }
        } else if (input.startsWith("delete")) {
            try {
                int taskID = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                return new DelCommand(taskID);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_FORMAT_MSG);
            }
        } else if (input.startsWith("todo")) {
            errMsg = "☹ OOPS!!! The description of a todo task cannot be empty!";
            String taskDescription = splitWithFormat(input, " ", errMsg)[1];
            return new AddCommand(new ToDoTask(taskDescription));
        } else if (input.startsWith("event")) {
            errMsg = "☹ OOPS!!! I need more details on the event!\n"
                    + "(Format: event _description_ /from _start_time_ /to _end_time_)";
            String taskDetailsStr = splitWithFormat(input, " ", errMsg)[1];
            String[] taskDetails = splitWithFormat(taskDetailsStr, "/from", errMsg);
            String taskDesription = taskDetails[0];
            String[] taskPeriod = splitWithFormat(taskDetails[1], "/to", errMsg); 
            try {
                LocalDateTime taskStart = LocalDateTime.parse(taskPeriod[0], DATETIME_FORMATTER);
                LocalDateTime taskEnd =  LocalDateTime.parse(taskPeriod[1], DATETIME_FORMATTER);
                return new AddCommand(new EventTask(taskDesription, taskStart, taskEnd));
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException(INVALID_TIME_FORMAT_MSG);
            }
        } else if (input.startsWith("deadline")) {
            errMsg = "☹ OOPS!!! I need more details on the event!\n"
            + "(Format: deadline _description_ /by _time_)";
            String taskDetailsStr = splitWithFormat(input, " ", errMsg)[1];
            String[] taskDetails = splitWithFormat(taskDetailsStr, "/by", errMsg);
            String taskDesription = taskDetails[0]; 
            try {
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[1], DATETIME_FORMATTER);
                return new AddCommand(new DeadlineTask(taskDesription, deadline));
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException(INVALID_TIME_FORMAT_MSG);
            }
        } else {
            throw new InvalidCommandException("☹ OOPS!!! I dont recognise this command!");
        }
    }



    private static String[] splitWithFormat(String input, String delimiter, String errMsg) 
            throws InvalidCommandException {
        String[] processedInput = input.trim().split(delimiter, 2);
        if (processedInput.length < 2) {
            throw new InvalidCommandException(errMsg);
        } 
        for (int i = 0; i < processedInput.length; i++) {
            processedInput[i] = processedInput[i].trim();
        }
        return processedInput;
    }
}
