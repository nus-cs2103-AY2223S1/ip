class Deadline extends Task {

    private String completeBy;

    Deadline(String description, String completeBy) {
        super(description, false);
        this.completeBy = completeBy;
    }

    Deadline(String descrpiton, boolean isDone, String completeBy) {

        super(descrpiton, isDone);
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.completeBy + ")";
    }

}