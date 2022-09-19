package duke.tasks;

import duke.services.Parser;

/**
 * Tasks with a deadline
 */
public class Deadline extends Task {

    /** Format: d MMM yyyy, h:mma */
    private String deadline;

    /**
     * Constructs a new Deadline with the given description and deadline
     *
     * @param description The description
     * @param deadline The deadline
     */
    public Deadline(String description, String deadline) {
        super(description, 'D');
        this.deadline = deadline;
    }

    /**
     * @return The deadline in the format that was entered
     */
    public String getEnteredDeadline() {
        return (deadline.indexOf(',') == -1)
                ? Parser.reformatDate(deadline, "d MMM yyyy", "d/M/yyyy")
                : Parser.reformatDateTime(deadline, "d MMM yyyy, h:mma", "d/M/yyyy h:mma");
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
