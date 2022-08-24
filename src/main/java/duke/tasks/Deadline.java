package duke.tasks;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getLetterTag() {
        return "D";
    }

    @Override
    public String getAdditionalInfo() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                + this.description + " (by: " + this.deadline + ")";
    }

}