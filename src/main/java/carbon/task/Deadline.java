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
 * Deadline class that represents tasks to be completed before a particular date or time.
 * Can be represented with either date alone, or date together with time.
 */
public class Deadline extends Task {
    public static final String FLAG = " /by";
    private static final int TYPEKEY = Task.getTypeKey(Task.Type.DEADLINE);

    private Temporal dateTime;

    private Deadline(String name, Boolean isDone, Temporal dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    private static String extractName(String input) throws CarbonException {
        int flagIndex = input.indexOf(Deadline.FLAG);
        if (flagIndex == -1) {
            CarbonException invalidFlag = new InvalidFlagException(input, Task.Type.DEADLINE);
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
     * @param input User text input.
     * @return Task object.
     */
    public static Task createTask(String input) {
        String name = Deadline.extractName(input);
        Temporal dateTime = Deadline.extractTime(input);
        Task deadline = new Deadline(name, false, dateTime);
        return deadline;
    }

    /**
     * Loads a task that was saved.
     *
     * @param name Name of the task.
     * @param isDone Whether the task is done or not.
     * @param time Date or time of the task.
     */
    public static Task loadTask(String name, Boolean isDone, String time) {
        Temporal dateTime = Deadline.formatTime(time);
        Task deadline = new Deadline(name, isDone, dateTime);
        return deadline;
    }

    /** {@inheritDoc} */
    @Override
    public String encode() {
        int typeKey = Deadline.TYPEKEY;
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        String type = "(DEAD)";
        String timeFormatted = this.displayTime(Task.FormatType.PRINT);
        return String.format("%s %s < %s", type, super.toString(), timeFormatted);
    }
}
