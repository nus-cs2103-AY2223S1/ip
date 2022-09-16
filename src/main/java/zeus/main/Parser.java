package zeus.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import zeus.command.AddDeadlineCommand;
import zeus.command.AddEventCommand;
import zeus.command.AddTodoCommand;
import zeus.command.Command;
import zeus.command.DeleteCommand;
import zeus.command.ExitCommand;
import zeus.command.FindCommand;
import zeus.command.ListCommand;
import zeus.command.TaskDoneCommand;
import zeus.command.TaskNotDoneCommand;
import zeus.command.UndoCommand;
import zeus.exception.ZeusException;


/**
 * Class that deals with making sense of the user commands.
 */
public class Parser {

    /**
     * Returns the corresponding Command from parsing the input String.
     *
     * @param fullCommand A String of input.
     * @return The corresponding Command.
     * @throws ZeusException If input is not in acceptable format.
     */
    public static Command parse(String fullCommand) throws ZeusException {

        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            int taskNum = Integer.parseInt(fullCommand.replaceFirst("mark", "").trim());
            return new TaskDoneCommand(taskNum - 1);
        } else if (fullCommand.startsWith("unmark")) {
            int taskNum = Integer.parseInt(fullCommand.replaceFirst("unmark", "").trim());
            return new TaskNotDoneCommand(taskNum - 1);
        } else if (fullCommand.startsWith("todo")) {
            String todoDescription = fullCommand.replaceFirst("todo", "").trim();
            if (todoDescription.isEmpty()) {
                throw new ZeusException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(todoDescription);
        } else if (fullCommand.startsWith("deadline")) {
            String[] deadlineInfo = fullCommand.replaceFirst("deadline ", "").split(" /by ");
            String dateAndTime = deadlineInfo[1];
            if (isValidDate(dateAndTime)) {
                LocalDateTime deadline = convertStringToDateTime(dateAndTime);
                return new AddDeadlineCommand(deadlineInfo[0], deadline);
            } else {
                return new AddDeadlineCommand(deadlineInfo[0], dateAndTime);
            }
        } else if (fullCommand.startsWith("event")) {
            String[] eventInfo = fullCommand.replaceFirst("event ", "").split(" /at ");
            String dateAndTime = eventInfo[1];
            if (isValidDate(dateAndTime)) {
                LocalDateTime eventDate = convertStringToDateTime(dateAndTime);
                return new AddEventCommand(eventInfo[0], eventDate);
            } else {
                return new AddEventCommand(eventInfo[0], eventInfo[1]);
            }
        } else if (fullCommand.startsWith("delete")) {
            int deleteIdx = Integer.parseInt(fullCommand.replaceFirst("delete", "").trim());
            return new DeleteCommand(deleteIdx - 1);
        } else if (fullCommand.startsWith("find")) {
            String s = fullCommand.replaceFirst("find", "").trim();
            return new FindCommand(s);
        } else if (fullCommand.startsWith("undo")) {
            return new UndoCommand();
        } else {
            throw new ZeusException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static LocalDateTime convertStringToDateTime(String datetime) {
        //String date = s.split(" ")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(datetime, formatter);
    }

    /**
     * Returns true if input is valid, returns false otherwise.
     *
     * @param dateInfo String representing date and time.
     * @return A boolean representing whether input string is valid.
     */
    private static boolean isValidDate(String dateInfo) {
        // Assume date is in the format 2022-09-08 1800
        String[] dateAndTime = dateInfo.split("-");
        if (dateAndTime.length != 3 && !isNumeric(dateAndTime[0]) || !isNumeric(dateAndTime[1])) {
            return false;
        }
        String[] yearTime = dateAndTime[2].split(" ");
        return yearTime.length == 2 && isNumeric(yearTime[0]) && isNumeric(yearTime[1]);
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null || strNum.equals("")) {
            return false;
        }
        try {
            int intValue = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
