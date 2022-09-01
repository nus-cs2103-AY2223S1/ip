package duke.task;
import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");
    protected static DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(by, dateTimeInputFormatter);
        } catch (Exception e) {
            throw new DukeException("Input your date and time in the format yyyy-MM-dd HHmm!");
        }
    }

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public static Deadline createDeadlineFromString(String line) {
        return new Deadline(line.substring(10, line.indexOf("(by:") - 1),
                LocalDateTime.parse(line.substring(line.indexOf("(by:") + 5, line.lastIndexOf(")")),
                        dateTimeOutputFormatter));
    }

    public boolean isOnThisDate(String dateStr) throws DukeException {
        if (dateStr.isBlank()) {
            throw new DukeException("Date cannot be blank!");
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, dateInputFormatter);
            return date.equals(this.dateTime.toLocalDate());
        } catch (Exception e){
            throw new DukeException("Please type in the date in the format yyyy-MM-dd");
        }
    }

    public boolean doesDescriptionContain(String input) throws DukeException {
        return Arrays.asList(description.split(" ")).contains(input);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(dateTimeOutputFormatter) + ")";
    }
}