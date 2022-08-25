package duke.tasks;

import duke.tools.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime dateAndTime;

    /**
     * Constructor for Event class.
     * @param description Description for event
     * @param dateAndTime Date and time of event
     */
    public Event(String description, Boolean isDone, LocalDateTime dateAndTime) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String taskToDataString() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("D | %s | %s | %s\n", isDone, super.description,
                Parser.formatDateTimeToData(dateAndTime));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                Parser.formatDateTimeToPrint(dateAndTime));
    }
}
