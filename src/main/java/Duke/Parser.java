package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Duke.Command.AddCommand;
import Duke.Command.Command;
import Duke.Command.DelCommand;
import Duke.Command.ExitCommand;
import Duke.Command.FindCommand;
import Duke.Command.InvalidCommandException;
import Duke.Command.ListCommand;
import Duke.Command.MarkCommand;
import Duke.Command.UnmarkCommand;
import Duke.Task.DeadlineTask;
import Duke.Task.EventTask;
import Duke.Task.ToDoTask;

public class Parser {

    /** The datetime format for user input. */
    public static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

    /** 
     * The error string to be displayed when the date time format provided by the
     * is wrong.
     */
    private static final String INVALID_TIME_FORMAT_MSG = 
            "☹ OOPS!!! Please use the following format for time dd.mm.yyyy hh:mm";

    /** 
     * The error string to be displayed when the number format provided by the
     * is wrong.
     */
    private static final String INVALID_NUMBER_FORMAT_MSG = 
            "☹ OOPS!!! Thats an invalid number!";

    /**
     * Parse the user input into a command.
     * 
     * @param input The user input
     * @return
     * @throws InvalidCommandException
     */
    public static Command parse(String input) throws InvalidCommandException {
        String[] commandComponents = input.split(" ", 2);
        assert commandComponents.length <= 2 : "Input should only be broken into at most 2 halves";
        assert commandComponents.length > 0 : "Input should have at least 1 element";
        String commandKeyword = commandComponents[0];
        String commandDetails = "";
        if (commandComponents.length < 2 
                && !(commandKeyword.equals("bye") || commandKeyword.equals("list"))) {
            throw new InvalidCommandException("I need more details!");
        } else if (commandComponents.length == 2) {
            commandDetails = commandComponents[1];
        } else {
            assert (commandComponents.length >=2 
                    || commandKeyword.equals("bye") 
                    || commandKeyword.equals("list")) 
                    : "Input should have at least 2 component unless it is list or bye";
        }
        

        switch (commandKeyword) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            try {
                int[] taskID = parseTaskIds(commandDetails);
                return new MarkCommand(taskID);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_FORMAT_MSG);
            }
        case "unmark":
            try {
                int[] taskID = parseTaskIds(commandDetails);
                return new UnmarkCommand(taskID);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_FORMAT_MSG);
            }
        case "delete":
            try {
                int[] taskID = parseTaskIds(commandDetails);
                return new DelCommand(taskID);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_FORMAT_MSG);
            }
        case "event":
            try {
                String errMsg = "☹ OOPS!!! I need more details on the event!\n"
                        + "(Format: event _description_ /from _start_time_ /to _end_time_)";
                String[] taskDetails = splitOnceWithFormat(commandDetails, "/from", errMsg);
                String taskDesription = taskDetails[0];
                String[] taskPeriod = splitOnceWithFormat(taskDetails[1], "/to", errMsg); 
                LocalDateTime taskStart = LocalDateTime.parse(taskPeriod[0], DATETIME_FORMATTER);
                LocalDateTime taskEnd =  LocalDateTime.parse(taskPeriod[1], DATETIME_FORMATTER);
                return new AddCommand(new EventTask(taskDesription, taskStart, taskEnd));
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException(INVALID_TIME_FORMAT_MSG);
            }
        case "deadline":
            try {
                String errMsg = "☹ OOPS!!! I need more details on the event!\n"
                        + "(Format: deadline _description_ /by _time_)";
                String[] taskDetails = splitOnceWithFormat(commandDetails, "/by", errMsg);
                String taskDesription = taskDetails[0]; 
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[1], DATETIME_FORMATTER);
                return new AddCommand(new DeadlineTask(taskDesription, deadline));
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException(INVALID_TIME_FORMAT_MSG);
            }
        case "todo":
            return new AddCommand(new ToDoTask(commandDetails));
        case "find":
            String keyword = commandDetails;
            return new FindCommand(keyword);
        default:
            throw new InvalidCommandException("☹ OOPS!!! I dont recognise this command!");
        }
    }

    /**
     * Checks that the string can be splitted into half and split it to fit the command.
     * 
     * @param input The user input line to be delimited.
     * @param delimiter The delimiter used to delimit the command.
     * @param errMsg The error message associated with the given command.
     * @return The splitted string.
     * @throws InvalidCommandException
     */
    private static String[] splitOnceWithFormat(String input, String delimiter, String errMsg) 
            throws InvalidCommandException {
        String[] processedInputs = input.trim().split(delimiter, 2);
        if (processedInputs.length < 2) {
            throw new InvalidCommandException(errMsg);
        }
        for (int i = 0; i < processedInputs.length; i++) {
            processedInputs[i] = processedInputs[i].trim();
        }
        return processedInputs;
    }

    private static int[] parseTaskIds(String command) {
        if (command.equals("*")) {
            int[] taskIds = {-1};
            return taskIds;
        }
        String[] textIds = command.replaceAll("[^0-9]", " ").split("\\s+");
        int[] taskIds = new int[textIds.length];
        for (int i = 0; i < textIds.length; i++) {
            taskIds[i] = Integer.parseInt(textIds[i]) - 1;
        }
        return taskIds;
    }
}
