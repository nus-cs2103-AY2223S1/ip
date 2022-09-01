package commands;

import common.ChatResponse;
import common.Parser;
import dukeexceptions.DukeException;
import dukeexceptions.MissingDescriptionException;
import dukeexceptions.WrongDatetimeFormatException;
import tasklist.TaskList;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class EventCommand extends Command {
    static final String EVENT_DATETIME_START_OR_END_FORMAT = "d/MM/uuuu HHmm";
    static final String EVENT_DATETIME_START_OR_END_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
    static final String EVENT_DATETIME_INPUT_FORMAT = "d/MM/uuuu HHmm - d/MM/uuuu HHmm";
    private final String[] args;

    public EventCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        String description = Parser.splitArrayIntoSubstrings(args, "/at").get(0);

        // ERROR HANDLING: Check for empty Tasks.Deadline description
        if (description.equalsIgnoreCase("")) {
            throw new MissingDescriptionException("event");
        }
        String unparsedTimeRange = Parser.splitArrayIntoSubstrings(args, "/at").get(1);
        String unparsedStartDateTime = unparsedTimeRange.split("-")[0].strip();
        String unparsedEndDateTime = unparsedTimeRange.split("-")[1].strip();

        if (!Parser.isValidDatetime(unparsedStartDateTime, EVENT_DATETIME_START_OR_END_FORMAT) || !Parser.isValidDatetime(unparsedEndDateTime, EVENT_DATETIME_START_OR_END_FORMAT)) {
            throw new WrongDatetimeFormatException(EVENT_DATETIME_INPUT_FORMAT);
        }
    }

    public static LocalDateTime parseEventDatetimeFromStorage(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(EVENT_DATETIME_START_OR_END_STORAGE_FORMAT).withResolverStyle(ResolverStyle.STRICT));
    }

    public static LocalDateTime parseEventDatetime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(EVENT_DATETIME_START_OR_END_FORMAT).withResolverStyle(ResolverStyle.STRICT));
    }

    @Override
    public String execute(TaskList taskList) {
        String description = Parser.splitArrayIntoSubstrings(this.args, "/at").get(0);

        String unparsedTimeRange = Parser.splitArrayIntoSubstrings(this.args, "/at").get(1);
        String unparsedStartDateTime = unparsedTimeRange.split("-")[0].strip();
        String unparsedEndDateTime = unparsedTimeRange.split("-")[1].strip();

        LocalDateTime startTimeRange = parseEventDatetime(unparsedStartDateTime);
        LocalDateTime endTimeRange = parseEventDatetime(unparsedEndDateTime);

        Event newEvent = new Event(description, startTimeRange, endTimeRange);
        taskList.addTask(newEvent);
        return ChatResponse.returnChatAddTask(newEvent, taskList);
    }
}
