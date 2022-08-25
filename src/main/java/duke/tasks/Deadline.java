package duke.tasks;

import duke.tools.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructor for Deadline class
     * @param description Description of deadline
     * @param date Due date of deadline
     */
    public Deadline(String description, Boolean isDone, LocalDate date) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.date = date;
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
                Parser.formatDateToData(date));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                Parser.formatDateToPrint(date));
    }
}
