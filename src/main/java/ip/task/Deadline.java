package ip.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ip.exception.BadDeadline;
import ip.exception.NoDeadline;
import ip.exception.NoDeadline;
import ip.exception.NoDescription;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM, HH:mm");

    public Deadline(Scanner options) throws NoDescription, NoDeadline, BadDeadline {
        if (options.hasNext()) {
            options.useDelimiter(" /by ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                try {
                    String dateInput = options.next();
                    super.describe(description);
                    super.unmark();
                    this.deadline = LocalDateTime.parse(dateInput, inputFormat);
                    System.out.println("CREATED DEADLINE: " + description + " DUE ON: " + deadline.format(outputFormat));
                } catch (DateTimeParseException e) {
                    System.out.println("INVALID DATE-TIME INPUT FORMAT DETECTED. ABORTING COMMAND.");
                    throw new BadDeadline();
                }
            } else {
                throw new NoDeadline();
            }
        } else {
            throw new NoDescription();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Deadline: " + deadline.format(outputFormat) + ")";
    }
}
