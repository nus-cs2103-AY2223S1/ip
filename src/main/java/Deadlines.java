import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private LocalDateTime deadline;
    private static DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor that creates Deadline object with specified description, deadline, and isDone status.
     *
     * @param description Description of Deadline.
     * @param deadline Timing of the Deadline.
     * @param isDone isDone status of the Deadline.
     * @throws DateTimeParseException Exception thrown when format of input date is invalid.
     */
    public Deadlines(String description, LocalDateTime deadline, boolean isDone) throws DateTimeParseException {
        super(isDone);
        this.description = description;
        this.deadline = deadline;
    }


    @Override
    public String processData() {
        String str;
        if (this.getIsDone()){
            str = String.format("D|true|%s|%s|", this.getDescription(), this.deadline.format(DATE_TIME_INPUT_FORMAT));
        } else {
            str = String.format("D|false|%s|%s|", this.getDescription(), this.deadline.format(DATE_TIME_INPUT_FORMAT));
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()){
            str = String.format("[D] %s [X] (by %s)", this.getDescription(),
                    this.deadline.format(DATE_TIME_OUTPUT_FORMAT));
        } else {
            str = String.format("[D] %s [ ] (by %s)", this.getDescription(),
                    this.deadline.format(DATE_TIME_OUTPUT_FORMAT));
        }
        return str;
    }

//    public Deadlines(String input)
//            throws MissingDescriptionException, MissingDeadlineException, DateTimeParseException {
//        super();
//        try {
//            //remove initial command
//            String sub = input.substring(9);
//            int timeIndex = sub.lastIndexOf("/by");
//            //get description part of input string
//            if (timeIndex == -1) {
//                throw new MissingDeadlineException();
//            }
//            String description = sub.substring(0, timeIndex - 1);
//            this.description = description;
//            String deadlineString = sub.substring(timeIndex + 4);
//            LocalDateTime deadline = LocalDateTime.parse(deadlineString, DATE_TIME_INPUT_FORMAT);
//            this.deadline = deadline;
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new MissingDescriptionException();
//        }
//    }
//
//    public Deadlines(String input, boolean isDone)
//            throws MissingDescriptionException, MissingDeadlineException, DateTimeParseException {
//        super(isDone);
//        try {
//            //remove initial command
//            String sub = input.substring(9);
//            int timeIndex = sub.lastIndexOf("/by");
//            //get description part of input string
//            if (timeIndex == -1) {
//                throw new MissingDeadlineException();
//            }
//            String description = sub.substring(0, timeIndex - 1);
//            this.description = description;
//            String deadlineString = sub.substring(timeIndex + 4);
//            LocalDateTime deadline = LocalDateTime.parse(deadlineString, DATE_TIME_INPUT_FORMAT);
//            this.deadline = deadline;
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new MissingDescriptionException();
//        }
//    }
//
//    public Deadlines(String description, String deadlineString, boolean isDone) throws DateTimeParseException {
//        super(isDone);
//        this.description = description;
//        LocalDateTime deadline = LocalDateTime.parse(deadlineString, DATE_TIME_INPUT_FORMAT);
//        this.deadline = deadline;
//    }
}
