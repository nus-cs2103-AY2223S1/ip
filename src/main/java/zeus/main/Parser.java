package zeus.main;

import java.time.LocalDate;

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
import zeus.exception.ZeusException;


/**
 * Class that deals with making sense of the user commands.
 */
public class Parser {


    /**
     * Static method that parses a String and returns the corresponding Command.
     *
     * @param fullCommand A String of input
     * @return The corresponding Command
     * @throws ZeusException If input is not in acceptable format
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
            String t = fullCommand.replaceFirst("todo", "").trim();
            if (t.isEmpty()) {
                throw new ZeusException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(t);
        } else if (fullCommand.startsWith("deadline")) {
            // sample input: deadline return book /by 2/12/2019 1800
            String[] deadlineInfo = fullCommand.replaceFirst("deadline ", "").split(" /by ");
            String dateAndTime = deadlineInfo[1];
            if (isSpecificDateFormat(dateAndTime)) {
                LocalDate localDate = convertFormattedStringToDate(dateAndTime);
                return new AddDeadlineCommand(deadlineInfo[0], localDate);
            } else {
                return new AddDeadlineCommand(deadlineInfo[0], dateAndTime);
            }
        } else if (fullCommand.startsWith("event")) {
            String[] eventInfo = fullCommand.replaceFirst("event ", "").split(" /at ");
            String dateAndTime = eventInfo[1];
            if (isSpecificDateFormat(dateAndTime)) {
                LocalDate ld = convertFormattedStringToDate(dateAndTime);
                return new AddEventCommand(eventInfo[0], ld);
            } else {
                return new AddEventCommand(eventInfo[0], eventInfo[1]);
            }
        } else if (fullCommand.startsWith("delete")) {
            int deleteIdx = Integer.parseInt(fullCommand.replaceFirst("delete", "").trim());
            return new DeleteCommand(deleteIdx - 1);
        } else if (fullCommand.startsWith("find")) {
            String s = fullCommand.replaceFirst("find", "").trim();
            return new FindCommand(s);
        } else {
            throw new ZeusException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static LocalDate convertFormattedStringToDate(String s) {
        String date = s.split(" ")[0];
        return LocalDate.parse(date);
    }

    /**
     * Check if input is in the required format to be converted to a Date.
     *
     * @param dateInfo String representing date
     * @return boolean representing whether input string is in a specific format
     */
    private static boolean isSpecificDateFormat(String dateInfo) {
        String[] dateAndTime = dateInfo.split(" ");
        String time = dateAndTime[1];
        if (!isNumeric(time)) {
            return false;
        }
        String[] dayMonthYear = dateAndTime[0].split("-");
        return isNumeric(dayMonthYear[0]) && isNumeric(dayMonthYear[1]) && isNumeric(dayMonthYear[2]);
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
