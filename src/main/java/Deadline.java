class Deadline extends Task {

    private String completeBy;

    Deadline(String description, String completeBy) {
        super(description, false);
        this.completeBy = completeBy;
    }

    Deadline(String description, boolean isDone, String completeBy) {

        super(description, isDone);
        this.completeBy = completeBy;
    }

    @Override
    public String toFileString() {

        return "D | " + (this.isDone ? 1 : 0) + " | " +
                this.description + " | " + this.completeBy;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.completeBy + ")";
    }

}