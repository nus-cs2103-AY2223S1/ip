package bobthebot.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageFormat() {
        int done = isDone ? 1 : 0;
        String res = String.format("T | %d | %s", done, taskName);
        return res;
    }
}
