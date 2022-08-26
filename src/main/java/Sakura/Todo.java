package Sakura;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String stringifyTask() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "\u001B[36m(TODO)\u001B[0m" + super.toString();
    }
}
