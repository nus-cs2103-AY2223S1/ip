package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;

import common.ChatResponse;
import common.Parser;
import dukeexceptions.DukeException;
import dukeexceptions.MissingDescriptionException;
import dukeexceptions.WrongDatetimeFormatException;
import tasklist.TaskList;
import tasks.Event;

/**
 * Represents an Event command.
 */
public class EventCommand extends Command {
    static final String EVENT_DATETIME_START_OR_END_FORMAT = "d/M/uuuu HHmm";
    static final String EVENT_DATETIME_START_OR_END_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
    static final String EVENT_DATETIME_INPUT_FORMAT = "d/M/uuuu HHmm - d/M/uuuu HHmm";
    private final String[] args;

    public EventCommand(String[] args) {
        this.args = args;
    }

    /**
     * Validates the passed arguments before executing the command.
     *
     * @param args Arguments to validate.
     * @throws DukeException Exception to be thrown if validation fails.
     */
    public static void validateArguments(String[] args) throws DukeException {
        assert args.length > 0 : "No arguments entered into EventCommand validateArguments";

        // ERROR HANDLING: Check for missing /at delimiter
        if (!Arrays.asList(args).contains("/at")) {
            throw new DukeException("Please include /at to describe the time range of this Event!");
        }

        String description = Parser.splitArrayIntoSubstrings(args, "/at").get(0);

        // ERROR HANDLING: Check for empty Tasks.Deadline description
        if (description.equalsIgnoreCase("")) {
            throw new MissingDescriptionException("event");
        }
        String unparsedTimeRange = Parser.splitArrayIntoSubstrings(args, "/at").get(1);
        String unparsedStartDateTime = unparsedTimeRange.split("-")[0].strip();
        String unparsedEndDateTime = unparsedTimeRange.split("-")[1].strip();

        assert !unparsedTimeRange.equals("") : "Parsing error occured in Event: time range";
        assert !unparsedStartDateTime.equals("") : "Parsing error occured in Event: start datetime";
        assert !unparsedEndDateTime.equals("") : "Parsing error occured in Event: end datetime";

        if (!Parser.isValidDatetime(unparsedStartDateTime, EVENT_DATETIME_START_OR_END_FORMAT)
                || !Parser.isValidDatetime(unparsedEndDateTime, EVENT_DATETIME_START_OR_END_FORMAT)) {
            throw new WrongDatetimeFormatException(EVENT_DATETIME_INPUT_FORMAT);
        }
    }

    /**
     * Returns LocalDateTime object parsed from given String.
     * Parsed string follows the DEADLINE_DATETIME_FORMAT format.
     * This parses datetime strings from user input.
     *
     * @param s String to parse.
     * @return Parsed LocalDateTime object.
     */
    public static LocalDateTime parseEventDatetime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(EVENT_DATETIME_START_OR_END_FORMAT)
                .withResolverStyle(ResolverStyle.STRICT));
    }

    /**
     * Returns LocalDateTime object parsed from given String in storage.
     * Parsed string follows the DEADLINE_STORAGE_FORMAat.
     * This parses datetime strings from storage.
     *
     * @param s String to parse.
     * @return Parsed LocalDateTime object.
     */
    public static LocalDateTime parseEventDatetimeFromStorage(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(EVENT_DATETIME_START_OR_END_STORAGE_FORMAT)
                .withResolverStyle(ResolverStyle.STRICT));
    }

    /**
     * Executes Event Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        String description = Parser.splitArrayIntoSubstrings(this.args, "/at").get(0);

        String unparsedTimeRange = Parser.splitArrayIntoSubstrings(this.args, "/at").get(1);
        String unparsedStartDateTime = Parser.splitString(unparsedTimeRange, "-", 0);
        String unparsedEndDateTime = Parser.splitString(unparsedTimeRange, "-", 1);

        LocalDateTime startTimeRange = parseEventDatetime(unparsedStartDateTime);
        LocalDateTime endTimeRange = parseEventDatetime(unparsedEndDateTime);

        Event newEvent = new Event(description, startTimeRange, endTimeRange);
        taskList.addTask(newEvent);
        return ChatResponse.returnChatAddTask(newEvent, taskList);
    }
}
