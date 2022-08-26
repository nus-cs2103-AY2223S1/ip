package carbon.task;

import carbon.error.CarbonException;
import carbon.error.InvalidFlagException;
import carbon.error.InvalidParamException;
import carbon.error.InvalidTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public class Deadline extends Task {
    public static final String FLAG = " /by";
    private static final int TYPEKEY = Task.getTypeKey(Task.Type.DEADLINE);

    private static String extractName(String input) throws CarbonException {
        int flagIndex = input.indexOf(Deadline.FLAG);
        if (flagIndex == -1) {
            CarbonException invalidFlag = new InvalidFlagException(input, "deadline");
            throw invalidFlag;
        } else {
            String name = input.substring("deadline ".length(), flagIndex);
            return name;
        }
    }

    private static Temporal extractTime(String input) throws CarbonException {
        int len = input.length();
        int flagIndex = input.indexOf(Deadline.FLAG);
        if (len <= flagIndex + Deadline.FLAG.length() + 1) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String timeString = input.substring(flagIndex + Deadline.FLAG.length() + 1);
            try {
                Temporal dateTime = Deadline.formatTime(timeString);
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
        String name = Deadline.extractName(input);
        Temporal dateTime = Deadline.extractTime(input);
        Task deadline = new Deadline(name, false, dateTime);
        return deadline;
    }

    public static Task loadTask(String name, Boolean isDone, String time) {
        Temporal dateTime = Deadline.formatTime(time);
        Task deadline = new Deadline(name, isDone, dateTime);
        return deadline;
    }

    private Temporal dateTime;
    
    private Deadline(String name, Boolean isDone, Temporal dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String encode() {
        int typeKey = Deadline.TYPEKEY;
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
                    ? time.format(Task.dateTimeFormat)
                    : time.format(Task.dateTimeFormatPrint);
        }
        return timeFormatted;
    }

    @Override
    public String toString() {
        String type = "\u001B[32m(DEAD)\u001B[0m";
        String timeFormatted = this.formatTime(Task.FormatType.PRINT);
        return String.format("%s %s < %s", type, super.toString(), timeFormatted);
    }
}
