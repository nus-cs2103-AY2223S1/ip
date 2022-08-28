package duke.tasks;

import duke.services.Parser;

/**
 * Tasks with a deadline
 */
public class Deadline extends Task {

    /** The deadline */
    private String deadline;

    /**
     * Constructs a new Deadline with the given description and deadline 
     *
     * @param description The task description
     * @param deadline The task deadline
     */
    public Deadline(String description, String deadline) {
        super(description, 'D');
        this.deadline = deadline;
    }

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
