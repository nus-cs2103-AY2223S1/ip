import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private LocalDateTime timing;
    private static DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Events(String input) throws MissingDescriptionException, MissingTimingException, DateTimeParseException {
        super();
        try {
            //remove initial command
            String sub = input.substring(6);
            int timeIndex = sub.lastIndexOf("/at");
            //get description part of input string
            if (timeIndex == -1) {
                throw new MissingTimingException();
            }
            String description = sub.substring(0, timeIndex - 1);
            this.description = description;
            String timingString = sub.substring(timeIndex + 4);
            LocalDateTime timing = LocalDateTime.parse(timingString, DATE_TIME_INPUT_FORMAT);
            this.timing = timing;
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }
    }

    public Events(String input, boolean isDone)
            throws MissingDescriptionException, MissingTimingException, DateTimeParseException {
        super(isDone);
        try {
            //remove initial command
            String sub = input.substring(6);
            int timeIndex = sub.lastIndexOf("/at");
            //get description part of input string
            if (timeIndex == -1) {
                throw new MissingTimingException();
            }
            String description = sub.substring(0, timeIndex - 1);
            this.description = description;
            String timingString = sub.substring(timeIndex + 4);
            LocalDateTime timing = LocalDateTime.parse(timingString, DATE_TIME_INPUT_FORMAT);
            this.timing = timing;
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        } catch (DateTimeParseException e) {
            System.out.println("Please input a valid date in the format: DD/MM/YYYY HHMM");
        }
    }

    public Events(String description, String timingString, boolean isDone) throws DateTimeParseException{
        super(isDone);
        this.description = description;
        LocalDateTime timing = LocalDateTime.parse(timingString, DATE_TIME_INPUT_FORMAT);
        this.timing = timing;
    }

    @Override
    String processData() {
        String str;
        if (this.getIsDone()){
            str = String.format("E|true|%s|%s|", this.getDescription(), this.timing.format(DATE_TIME_INPUT_FORMAT));
        } else {
            str = String.format("E|false|%s|%s|", this.getDescription(), this.timing.format(DATE_TIME_INPUT_FORMAT));
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()){
            str = String.format("[E] %s [X] (at %s)", this.getDescription(),
                    this.timing.format(DATE_TIME_OUTPUT_FORMAT));
        } else {
            str = String.format("[E] %s [ ] (at %s)", this.getDescription(),
                    this.timing.format(DATE_TIME_OUTPUT_FORMAT));
        }
        return str;
    }
}
