package duke.parser;

import static duke.common.Messages.DATE_TIME_FORMAT;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.CancelCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.common.AnomaliesManager;
import duke.common.InputChecker;
import duke.ui.BotUI;

/**
 * Deals with making sense of the user input.
 * Contains methods that return Command for execution in Duke class and extracting information from user's raw input.
 */
public class Parser {

    private static final BotUI UI = new BotUI();
    private static final int NUMBER_OF_SECTION_SPLIT = 2;
    private static final int MINIMUM_COMMAND_SECTION = 1;
    private static final int FIRST_ITEM_AFTER_SPLIT = 0;
    private static final int SECOND_ITEM_AFTER_SPLIT = 1;
    private static final int HOURS_IN_TIME = 100;
    private static final int MINUTES_IN_TIME = 100;


    private static String[] splitInput(String input, String regex) {
        return input.split(regex, NUMBER_OF_SECTION_SPLIT);
    }

    private static int extractHours(int time) {
        return time / HOURS_IN_TIME;
    }

    private static int extractMinutes(int time) {
        return time % MINUTES_IN_TIME;
    }

    private static Command checkValidAnswer(String rawInput, AnomaliesManager anomaliesManager) {
        String trimInput = rawInput.trim();
        switch (trimInput) {
        case "Y":
            return anomaliesManager.getPrevCommand().resolveAnomaly();
        case "N":
            return new CancelCommand(trimInput);
        default:
            return new UnknownCommand(trimInput, true);
        }
    }

    /**
     * Returns different type of Command according to the user raw input.
     *
     * @param rawInput User's raw input.
     * @return Command to be executed in Duke class.
     * @throws DukeException - Thrown if user command is invalid.
     */
    public static Command parse(String rawInput, AnomaliesManager anomaliesManager) throws DukeException {
        try {
            if (anomaliesManager.isRaised()) {
                anomaliesManager.resolveAnomalies();
                return checkValidAnswer(rawInput, anomaliesManager);
            }

            String[] commandAndDetail = rawInput.split(" ", NUMBER_OF_SECTION_SPLIT);
            String command = commandAndDetail[FIRST_ITEM_AFTER_SPLIT];
            if (commandAndDetail.length == MINIMUM_COMMAND_SECTION) {
                if (command.equals("list")) {
                    return new ListCommand(command);
                } else if (command.equals("bye")) {
                    return new ExitCommand(command);
                }
            }

            String detail = commandAndDetail[SECOND_ITEM_AFTER_SPLIT];
            if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                InputChecker.checkInput(rawInput);
                return new AddCommand(command, detail);
            } else if (command.equals("mark") || command.equals("unmark")) {
                return new MarkCommand(command, detail);
            } else if (command.equals("delete")) {
                return new DeleteCommand(command, detail);
            } else if (command.equals("find")) {
                return new FindCommand(command, detail);
            } else {
                return new UnknownCommand(command);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(UI.invalidFormat());
        }
    }

    /**
     * Returns LocalDateTime for Event and Deadline tasks.
     *
     * @param filteredInput User's filtered input which command is extracted (e.g. someDetails /by 2022-08-25 1800).
     * @return LocalDateTime of the filtered input.
     * @throws DukeException - Thrown if the date/time format is invalid (e.g. 2022-08-251800) which
     *                       cause DateTimeException and IndexOutOfBoundsException during the process.
     */
    public static LocalDateTime extractDateTime(String filteredInput, String detailDateSplitter) throws DukeException {
        boolean isValidSplitter = detailDateSplitter.trim().equals("/by") || detailDateSplitter.trim().equals("/at");
        assert isValidSplitter : "invalid Splitter";
        try {
            String filterDate = splitInput(filteredInput, detailDateSplitter)[SECOND_ITEM_AFTER_SPLIT];
            String[] dateAndTime = filterDate.split(" ");
            int time = Integer.parseInt(dateAndTime[SECOND_ITEM_AFTER_SPLIT]);
            int hours = extractHours(time);
            int minutes = extractMinutes(time);
            String[] splitDate = dateAndTime[FIRST_ITEM_AFTER_SPLIT].split("-");
            ArrayList<Integer> dateInfo = new ArrayList<>();
            for (String s : splitDate) {
                dateInfo.add(Integer.parseInt(s));
            }
            return LocalDateTime.of(dateInfo.get(0), dateInfo.get(1), dateInfo.get(2),
                    hours, minutes);
        } catch (DateTimeException | IndexOutOfBoundsException ex) {
            throw new DukeException(UI.invalidDateFormat());
        }
    }

    /**
     * Returns detail for Events and Deadlines tasks.
     *
     * @param filteredInput User's filtered input which command is extracted (e.g. someDetails /by 2022-08-25 1800).
     * @param detailDateSplitter Detail and date splitter of Event and Deadline tasks ("/by" or "/at).
     * @return String of task's detail.
     */
    public static String extractDetail(String filteredInput, String detailDateSplitter) {
        boolean isValidSplitter = detailDateSplitter.trim().equals("/by") || detailDateSplitter.trim().equals("/at");
        assert isValidSplitter : "invalid Splitter";
        return splitInput(filteredInput, detailDateSplitter)[FIRST_ITEM_AFTER_SPLIT];
    }

    /**
     * Returns LocalDateTime converted from the tasks wrote in previous running of duke.
     *
     * @param timeString Date/Time String stored in the .txt file.
     * @return LocalDateTime of the stored String in the .txt file.
     * @see duke.storage.FileManager For the usage of this method.
     */
    public static LocalDateTime convertTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.parse(timeString, formatter);
    }

}
