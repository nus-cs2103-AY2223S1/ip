package poolsheen.task;

public class ToDo extends Task {
    /**
     * A public constructor to initialise a ToDo task.
     * @param description The details of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String[] toArr() {
        return new String[]{"T", this.getStatusIcon(), this.description};
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}