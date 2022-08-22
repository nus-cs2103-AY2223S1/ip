package duke.tasks;

public class Deadline extends Task {

    private String deadline;

    /**
     * Standard constructor for a deadline
     * @param description The description of the task
     * @param deadline The deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor that allows for creation of pre-completed deadline tasks
     * @param description The description of the task
     * @param isDone Marks whether the task has been completed before
     * @param deadline The deadline of the task
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getSaveString() {
        return "DEADLINE,," + super.getSaveString() + this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
