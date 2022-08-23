package duke;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + this.getStatusIcon() + " " + super.description;
    }

    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "T|1|" + super.description + "\n";
        } else {
            return "T|0|" + super.description + "\n";
        }
    }
}
