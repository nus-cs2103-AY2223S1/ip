package duke.task;

public class Todos extends Task {

    public Todos(String description) {
        super(description);
    }

    public String storedTaskString() {
        return "T|" + String.valueOf(this.isDone) + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
