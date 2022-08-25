package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.common.InputChecker;
import duke.ui.BotUI;

/**
 * Deals with making sense of the user input.
 * Contains methods that return Command for execution in Duke class and extracting information from user's raw input.
 */

public class Parser {

    private static final BotUI UI = new BotUI();

    private static String[] splitInput(String input, String regex) {
        return input.split(regex, 2);
    }

    /**
     * Returns different type of Command according to the user raw input.
     *
     * @param rawInput user's raw input.
     * @return Command to be executed in Duke class.
     * @throws DukeException - thrown if user command is invalid.
     */
    public static Command parse(String rawInput) throws DukeException {
        try {
            String[] commandAndDetail = rawInput.split(" ", 2);
            String command = commandAndDetail[0];
            if (commandAndDetail.length < 2) {
                if (command.equals("list")) {
                    return new ListCommand(command);
                } else if (command.equals("bye")) {
                    return new ExitCommand(command);
                }
            }
            String detail = commandAndDetail[1];
            if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                InputChecker.checkInput(rawInput);
                return new AddCommand(command, detail);
            } else if (command.equals("mark") || command.equals("unmark")) {
                return new MarkCommand(command, detail);
            } else if (command.equals("delete")) {
                return new DeleteCommand(command, detail);
            } else {
                throw new DukeException(UI.invalidFormat());
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(UI.invalidFormat());
        }
    }

    /**
     * Returns LocalDateTime for Events and Deadlines tasks.
     *
     * @param filteredInput user's filtered input which command is extracted (e.g. someDetails /by 2022-08-25 1800)
     * @return LocalDateTime of the filtered input.
     * @throws DukeException - thrown if the date/time format is invalid (e.g. 2022-08-251800) which
     *                       cause DateTimeException and IndexOutOfBoundsException during the process.
     */
    public static LocalDateTime extractDateTime(String filteredInput, String timeIdentifier) throws DukeException {
        try {
            String filterDate = splitInput(filteredInput, timeIdentifier)[1];
            String[] dateAndTime = filterDate.split(" ");
            int time = Integer.parseInt(dateAndTime[1]);
            int hours = time / 100;
            int minutes = time % 100;
            String[] splitDate = dateAndTime[0].split("-");
            ArrayList<Integer> dateInfo = new ArrayList<Integer>();
            for (String s : splitDate) {
                dateInfo.add(Integer.parseInt(s));
            }
            return LocalDateTime.of(dateInfo.get(0), dateInfo.get(1), dateInfo.get(2),
                    hours, minutes);
        } catch (DateTimeException ex) {
            throw new DukeException(UI.invalidDateFormat());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(UI.invalidDateFormat());
        }
    }

    /**
     * Returns detail for Events and Deadlines tasks.
     *
     * @param filteredInput user's filtered input which command is extracted (e.g. someDetails /by 2022-08-25 1800)
     * @return String of task's detail
     */
    public static String extractDetail(String filteredInput, String timeIdentifier) {
        return splitInput(filteredInput, timeIdentifier)[0];
    }

    /**
     * Returns LocalDateTime converted from the tasks wrote in previous running of duke.
     *
     * @param timeString date/time String stored in the .txt file.
     * @return LocalDateTime of the stored String in the .txt file.
     * @see duke.storage.FileManager for the usage of this method.
     */
    public static LocalDateTime convertTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return LocalDateTime.parse(timeString, formatter);
    }

}
