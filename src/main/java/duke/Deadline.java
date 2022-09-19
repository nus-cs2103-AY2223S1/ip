package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final char tag = 'D';
    public static final String DELIMITER = " /by ";
    private String time;
    private LocalDate date;

    public Deadline(String description) {
        super(description.split(Deadline.DELIMITER)[0].substring(9));
        this.time = description.split(Deadline.DELIMITER)[1];
        this.date = LocalDate.parse(this.time);
    }

    @Override
    public String printTask() {
        return String.format("[%s]%s (by: %s)",
                this.tag,
                super.printTask(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
