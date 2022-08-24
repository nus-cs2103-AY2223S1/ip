import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    public static String FLAG = " /by";
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm"
            );
    // TODO: allow including time and also excluding time
    private LocalDate time;

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

    private static LocalDate extractTime(String input) throws CarbonException {
        int len = input.length();
        int flagIndex = input.indexOf(Deadline.FLAG);
        if (len <= flagIndex + Deadline.FLAG.length() + 1) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String timeString = input.substring(flagIndex + Deadline.FLAG.length() + 1);
            try {
                LocalDate time = LocalDate.parse(timeString, Deadline.timeFormat);
                return time;
            } catch (DateTimeParseException error) {
                CarbonException invalidTime = new InvalidTimeException(timeString);
                throw invalidTime;
            }
        }
    }

    public Deadline(String input) {
        super(Deadline.extractName(input));
        this.time = Deadline.extractTime(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[32m(DEAD)\u001B[0m";
        String timeFormatted = this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("%s %s < %s", type, super.toString(), timeFormatted);
    }
}
