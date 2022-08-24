import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

class Deadline extends Task {
    public static final String FLAG = " /by";

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

    public Deadline(String input) {
        super(Deadline.extractName(input));
        this.dateTime = Deadline.extractTime(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[32m(DEAD)\u001B[0m";
        String timeFormatted;
        if (this.dateTime instanceof LocalDate) {
            LocalDate date = (LocalDate) this.dateTime;
            timeFormatted = date.format(Task.dateFormatPrint);
        } else {
            LocalDateTime time = (LocalDateTime) this.dateTime;
            timeFormatted = time.format(Task.dateTimeFormatPrint);
        }
        return String.format("%s %s < %s", type, super.toString(), timeFormatted);
    }
}
