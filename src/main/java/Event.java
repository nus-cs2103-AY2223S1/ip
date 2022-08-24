import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

class Event extends Task {
    public static final String FLAG = " /at";

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
                if (timeString.length() < 11) {
                    LocalDate time = LocalDate.parse(timeString, Task.dateFormat);
                    return time;
                } else {
                    LocalDateTime time = LocalDateTime.parse(
                            timeString, 
                            Task.dateTimeFormat);
                    System.out.println(time);
                    return time;
                }
            } catch (DateTimeParseException error) {
                CarbonException invalidTime = new InvalidTimeException(timeString);
                throw invalidTime;
            }
        }
    }

    private Temporal dateTime;

    public Event(String input) {
        super(Event.extractName(input));
        this.dateTime = Event.extractTime(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[34m(EVNT)\u001B[0m";
        String timeFormatted;
        if (this.dateTime instanceof LocalDate) {
            LocalDate date = (LocalDate) this.dateTime;
            timeFormatted = date.format(Task.dateFormatPrint);
        } else {
            LocalDateTime time = (LocalDateTime) this.dateTime;
            timeFormatted = time.format(Task.dateTimeFormatPrint);
        }
        return String.format("%s %s @ %s", type, super.toString(), timeFormatted);
    }
}
