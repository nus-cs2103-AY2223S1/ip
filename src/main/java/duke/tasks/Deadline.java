package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends Task {

    protected LocalDateTime date;
    private final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");

    /**
     * Creates Deadline object from user input.
     * @param description the task description.
     * @param by the timing information.
     * @throws DukeException when provided with invalid input from user.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.date = dateTimeParser(by);
    }

    /**
     * Creates Deadline object from file input.
     * @param description the task description.
     * @param by the timing information.
     * @param format the datetime format.
     */
    public Deadline(String description, String by, DateTimeFormatter format) {
        super(description);
        this.date = LocalDateTime.parse(by, format);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(this.OUTPUT_FORMAT) + ")";
    }

    @Override
    public String getDate() {
        return this.date.format(this.OUTPUT_FORMAT);
    }

    private static LocalDateTime dateTimeParser(String time) throws DukeException {
        try {
            String[] timing = time.split(" ", 2);
            String[] dayMonYr = timing[0].split("/", 3);
            int hr = Integer.valueOf(timing[1].substring(0, 2));
            int minute = Integer.valueOf(timing[1].substring(2));
            return LocalDateTime.of(Integer.valueOf(dayMonYr[2]),
                    Integer.valueOf(dayMonYr[1]),
                    Integer.valueOf(dayMonYr[0]),
                    hr, minute);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException
                | StringIndexOutOfBoundsException | DateTimeException e) {
            throw new DukeException("Invalid deadline input, format should be deadline <task> /by dd/mm/yyyy HHmm");
        }
    }
}
