package duke;

public class Todo extends Task {

    private final char type = 'T';

    public Todo(String taskname) {
        super(taskname);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.type, super.toString());
    }

    @Override
    public String toSavedString() {
        return "" + this.type + "#" + super.toSavedString();
    }
}
