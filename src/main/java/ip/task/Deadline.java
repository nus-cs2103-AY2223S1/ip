package ip.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ip.exception.BadDeadline;
import ip.exception.MissingDescription;

/**
 * Encapsulation of a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;
    /** Format to use for parsing date time input. */
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /** Format to use for outputting date time input. */
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM, HH:mm");

    /**
     * Constructor to create a deadline object.
     *
     * @param taskMetadata Contains the description and deadline of the task.
     * @throws MissingDescription If there is no description in options.
     * @throws BadDeadline If the deadline is missing or in the wrong format.
     */
    public Deadline(Scanner taskMetadata) throws MissingDescription, BadDeadline {
        // No deadline description
        if (!taskMetadata.hasNext()) {
            throw new MissingDescription();
        }
        taskMetadata.useDelimiter(" /by ");
        String taskDescription = taskMetadata.next().substring(1);
        String userSpecifiedDeadline = "";
        // No deadline specified
        if (!taskMetadata.hasNext()) {
            throw new BadDeadline(userSpecifiedDeadline);
        }
        try {
            userSpecifiedDeadline = taskMetadata.next();
            super.setTaskDescription(taskDescription);
            super.markIncomplete();
            this.deadline = LocalDateTime.parse(userSpecifiedDeadline, inputFormat);
        } catch (DateTimeParseException e) {
            throw new BadDeadline(userSpecifiedDeadline);
        }
    }

    /**
     * Constructor to create deadline object from formatted string.
     *
     * @param taskMetadata Contains data used to build the deadline object.
     */
    public Deadline(String[] taskMetadata) {
        super.setTaskDescription(taskMetadata[2]);
        this.deadline = LocalDateTime.parse(taskMetadata[3], inputFormat);
        if (taskMetadata[1].equals("true")) {
            super.markComplete();
        }
    }

    public String formatToSave() {
        return "d|" + isComplete + "|" + taskDescription + "|" + deadline.format(inputFormat) + "|\n";
    }

    @Override
    public boolean containsString(String keyword) {
        return super.taskDescription.contains(keyword);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Deadline: " + deadline.format(outputFormat) + ")";
    }
}
