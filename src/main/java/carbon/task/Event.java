package carbon.task;

import carbon.error.CarbonException;
import carbon.error.InvalidFlagException;
import carbon.error.InvalidParamException;
import carbon.error.InvalidTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public class Event extends Task {
    public static final String FLAG = " /at";
    private static final int TYPEKEY = Task.getTypeKey(Task.Type.EVENT);

    private static String extractName(String input) {
        int flagIndex = input.indexOf(Event.FLAG);
        if (flagIndex == -1) {
            CarbonException invalidFlag = new InvalidFlagException(input, "event");
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
                LocalDate time = LocalDate.parse(timeString, Task.dateFormat);
                return time;
            } else {
                LocalDateTime time = LocalDateTime.parse(
                        timeString, 
                        Task.dateTimeFormat);
                return time;
            }
        } catch (DateTimeParseException error) {
            throw error;
        }
    }


    public static Task createTask(String input) {
        String name = Event.extractName(input);
        Temporal dateTime = Event.extractTime(input);
        Task event = new Event(name, false, dateTime);
        return event;
    }

    public static Task loadTask(String name, Boolean isDone, String time) {
        Temporal dateTime = Event.formatTime(time);
        Task event = new Event(name, isDone, dateTime);
        return event;
    }

    private Temporal dateTime;

    private Event(String name, Boolean isDone, Temporal dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String encode() {
        int typeKey = Event.TYPEKEY;
        int isDone = this.isDone ? 1 : 0;
        String result = String.format(
                "%d|%d|%s|%s\n", typeKey, isDone, this.name, 
                this.formatTime(Task.FormatType.READ));
        return result;
    }

    private String formatTime(Task.FormatType formatType) {
        String timeFormatted;
        if (this.dateTime instanceof LocalDate) {
            LocalDate date = (LocalDate) this.dateTime;
            timeFormatted = formatType == Task.FormatType.READ
                    ? date.format(Task.dateFormat)
                    : date.format(Task.dateFormatPrint);
        } else {
            LocalDateTime time = (LocalDateTime) this.dateTime;
            timeFormatted = formatType == Task.FormatType.READ
                    ? time.format(Task.dateFormat)
                    : time.format(Task.dateFormatPrint);
        }
        return timeFormatted;
    }

    @Override
    public String toString() {
        String type = "\u001B[34m(EVNT)\u001B[0m";
        String timeFormatted = this.formatTime(Task.FormatType.PRINT);
        return String.format("%s %s @ %s", type, super.toString(), timeFormatted);
    }
}
