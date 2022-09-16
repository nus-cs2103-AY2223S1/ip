package duke.task;

public class Todos extends Task {

    public Todos(String description) {
        super(description);
    }

    @Override
    public String savedTaskString() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
