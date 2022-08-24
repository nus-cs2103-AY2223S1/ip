import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task {
    public static String FLAG = " /at";
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm"
            );
    private LocalDate time;

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

    private static LocalDate extractTime(String input) throws CarbonException {
        int len = input.length();
        int flagIndex = input.indexOf(Event.FLAG);
        if (len <= flagIndex + Event.FLAG.length() + 1) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String timeString = input.substring(flagIndex + Event.FLAG.length() + 1);
            try {
                LocalDate time = LocalDate.parse(timeString, Event.timeFormat);
                return time;
            } catch (DateTimeParseException error) {
                CarbonException invalidTime = new InvalidTimeException(timeString);
                throw invalidTime;
            }
        }
    }

    public Event(String input) {
        super(Event.extractName(input));
        this.time = Event.extractTime(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[34m(EVNT)\u001B[0m";
        String timeFormatted = this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("%s %s @ %s", type, super.toString(), timeFormatted);
    }
}
