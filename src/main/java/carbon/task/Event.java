package carbon.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import carbon.error.CarbonException;
import carbon.error.InvalidFlagException;
import carbon.error.InvalidParamException;
import carbon.error.InvalidTimeException;

/**
 * Encapsulates information regarding Events.
 */
public class Event extends Task {
    public static final String FLAG = " /at";
    private static final int TYPEKEY = Task.getTypeKey(Task.Type.EVENT);

    private Temporal dateTime;

    private Event(String name, Boolean isDone, Temporal dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    private static String extractName(String input) {
        int flagIndex = input.indexOf(Event.FLAG);
        if (flagIndex == -1) {
            CarbonException invalidFlag = new InvalidFlagException(input, Task.Type.EVENT);
            throw invalidFlag;
        } else {
            String name = input.substring("event ".length(), flagIndex);
            return name;
        }
    }

    private static Temporal extractTime(String input) throws CarbonException {
        int len = input.length();
        int flagIndex = input.indexOf(Event.FLAG);
        if (len <= flagIndex + Event.FLAG.length() + 1) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String timeString = input.substring(flagIndex + Event.FLAG.length() + 1);
            try {
                Temporal dateTime = Event.formatTime(timeString);
                return dateTime;
            } catch (DateTimeParseException error) {
                CarbonException invalidTime = new InvalidTimeException(timeString);
                throw invalidTime;
            }
        }
    }

    private static Temporal formatTime(String timeString) throws DateTimeParseException {
        try {
            if (timeString.length() < 11) {
                LocalDate time = LocalDate.parse(timeString, Task.DATEFORMAT);
                return time;
            } else {
                LocalDateTime time = LocalDateTime.parse(
                        timeString,
                        Task.DATETIMEFORMAT);
                return time;
            }
        } catch (DateTimeParseException error) {
            throw error;
        }
    }

    /**
     * Creates a new Task object.
     *
     * @param input User input.
     * @return Task object.
     */
    public static Task createTask(String input) {
        String name = Event.extractName(input);
        Temporal dateTime = Event.extractTime(input);
        Task event = new Event(name, false, dateTime);
        return event;
    }

    /**
     * Loads a task that was saved.
     *
     * @param name Name of the task.
     * @param isDone Whether the task is done or not.
     * @param time Date or time of the task.
     * @return Task object.
     */
    public static Task loadTask(String name, Boolean isDone, String time) {
        Temporal dateTime = Event.formatTime(time);
        Task event = new Event(name, isDone, dateTime);
        return event;
    }

    /** @inheritDoc */
    @Override
    public String encode() {
        int typeKey = Event.TYPEKEY;
        int isDone = this.isDone ? 1 : 0;
        String result = String.format(
                "%d|%d|%s|%s\n", typeKey, isDone, this.name,
                this.displayTime(Task.FormatType.READ));
        return result;
    }

    private String displayTime(Task.FormatType formatType) {
        String timeFormatted;
        if (this.dateTime instanceof LocalDate) {
            LocalDate date = (LocalDate) this.dateTime;
            timeFormatted = formatType == Task.FormatType.READ
                    ? date.format(Task.DATEFORMAT)
                    : date.format(Task.DATEFORMATPRINT);
        } else {
            LocalDateTime time = (LocalDateTime) this.dateTime;
            timeFormatted = formatType == Task.FormatType.READ
                    ? time.format(Task.DATETIMEFORMAT)
                    : time.format(Task.DATETIMEFORMATPRINT);
        }
        return timeFormatted;
    }

    /** @inheritDoc */
    @Override
    public String toString() {
        String type = "\u001B[34m(EVNT)\u001B[0m";
        String timeFormatted = this.displayTime(Task.FormatType.PRINT);
        return String.format("%s %s @ %s", type, super.toString(), timeFormatted);
    }
}
